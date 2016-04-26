package hust.nursenfcclient.init;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import de.greenrobot.event.EventBus;
import hust.nursenfcclient.MainActivity;
import hust.nursenfcclient.R;
import hust.nursenfcclient.alarm.AlarmEvent;
import hust.nursenfcclient.daoutils.DaoUtils;
import hust.nursenfcclient.database.NurseNFCDatabaseHelper;
import hust.nursenfcclient.http.HttpUtils;
import hust.nursenfcclient.http.image.PicassoUtils;
import hust.nursenfcclient.imageloader.ImageHelper;
import hust.nursenfcclient.network.ServicesHelper;
import hust.nursenfcclient.utils.LogUtils;
import hust.nursenfcclient.utils.SharedHelper;
import nurse_db.NurseInfo;
import nurse_db.NurseInfoDao;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2015/11/21.
 */
public class CheckInActivity extends BaseActivity{
    @Bind(R.id.nurseNameText) TextView nurseNameText;
    @Bind(R.id.nurseGenderText) TextView nurseGenderText;
    @Bind(R.id.nurseAgeText) TextView nurseAgeText;
    @Bind(R.id.nurseProjectText) TextView nurseProjectText;
    @Bind(R.id.nurseIdText) TextView nurseIdText;
    @Bind(R.id.nurseIdPhoto) ImageView nurseIdPhoto;
    @Bind(R.id.errorBt) Button errorBt;
    @Bind(R.id.correctBt) Button correctBt;

    private SharedHelper sharedHelper;
    private DaoUtils daoUtils;
    private PicassoUtils picassoUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkin_layout);
        sharedHelper = SharedHelper.getSharedHelper(this);
        daoUtils = DaoUtils.getInstance(this);
        picassoUtils = PicassoUtils.getInstance(this);

        ButterKnife.bind(this);

        initData();
    }

    // 初始化数据
    private void initData() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                // 获取NurseID
                String nurseId = sharedHelper.getString(SharedHelper.CURRENT_USER_NAME);
                subscriber.onNext(nurseId);
            }
        }) .map(new Func1<String, NurseInfo>() {
            @Override
            public NurseInfo call(String s) {
                // 查询数据库
                NurseInfoDao dao = daoUtils.getDaoSession().getNurseInfoDao();
                QueryBuilder<NurseInfo> builder = dao.queryBuilder();
                WhereCondition condition = NurseInfoDao.Properties.Nurse_id.eq(s);
                builder.where(condition);
                Query<NurseInfo> query = builder.build();
                List<NurseInfo> infos = query.list();
                if (infos != null) {
                    NurseInfo info = infos.get(0);
                    return info;
                }
                return null;
            }
        })  .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<NurseInfo>() {
                @Override
                public void onCompleted() {

                }
                @Override
                public void onError(Throwable e) {
                    LogUtils.e(e.toString());
                }
                @Override
                public void onNext(NurseInfo nurseInfo) {
                    if (nurseInfo != null) {
                        nurseNameText.setText(nurseInfo.getNurse_name());
                        nurseIdText.setText(nurseInfo.getNurse_id());
                        nurseGenderText.setText(nurseInfo.getNurse_gender());
                        nurseAgeText.setText(String.valueOf(nurseInfo.getNurse_age()));
                        nurseProjectText.setText(nurseInfo.getNurse_major());

                        // 加载图片
                        String photoUrl = nurseInfo.getNurse_photo();
                        picassoUtils.loadImage(photoUrl, nurseIdPhoto);
                    }
                }
            });
    }


    @OnClick(R.id.errorBt)
    void onErrorClick() {
        startActivity(new Intent(this, LogInActivity.class));
        this.finish();
    }

    @OnClick(R.id.correctBt)
    void onCorrectClick() {
        startActivity(new Intent(this, MainActivity.class));

        // 同时注意开启提醒功能
        EventBus.getDefault().post(new AlarmEvent(AlarmEvent.ACTION_START_ALARM));
        // 开启数据库维护线程
        EventBus.getDefault().post(new AlarmEvent(AlarmEvent.ACTION_STRAT_UPDATE_DB));
        this.finish();
    }
}
