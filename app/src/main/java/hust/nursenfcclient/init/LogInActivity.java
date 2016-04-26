package hust.nursenfcclient.init;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hust.nursenfcclient.MainActivity;
import hust.nursenfcclient.R;
import hust.nursenfcclient.daoutils.DaoUtils;
import hust.nursenfcclient.http.HttpUtils;
import hust.nursenfcclient.http.okhttp.OkHttpClientUtils;
import hust.nursenfcclient.login.LoadingFragment;
import hust.nursenfcclient.login.LoginService;
import hust.nursenfcclient.login.UserInfo;
import hust.nursenfcclient.setting.NetSetActivity;
import hust.nursenfcclient.utils.LogUtils;
import hust.nursenfcclient.utils.SharedHelper;
import hust.nursenfcclient.utils.ToastUtils;
import nurse_db.NurseInfo;
import nurse_db.NurseInfoDao;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2015/11/20.
 */
/*登录界面*/
public class LogInActivity extends BaseActivity {
    @Bind(R.id.logoImg) ImageView logoImg;
    @Bind(R.id.idEditText) EditText idEditText;
    @Bind(R.id.loginBt) Button loginBt;
    @Bind(R.id.setNetText) TextView setNetText;

    private SharedHelper sharedHelper;
    private String nurseId;
    private DaoUtils daoUtils;
    private LoadingFragment loadingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        sharedHelper = SharedHelper.getSharedHelper(this);
        daoUtils = DaoUtils.getInstance(this);
        initView();
    }


    private void initView() {
        ButterKnife.bind(this);
        String nurseId = sharedHelper.getString(SharedHelper.CURRENT_USER_NAME, MainActivity.DEFAULT_NURSE_ID);
        idEditText.setText(nurseId);
        idEditText.setSelection(nurseId.length());
        // 设置下划线
        setNetText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        setNetText.getPaint().setAntiAlias(true);//抗锯齿
    }

    @OnClick(R.id.setNetText)
    void onSetNetText() {
        startActivity(new Intent(this, NetSetActivity.class));
    }

    @OnClick(R.id.loginBt)
    void onLogInClick() {
        String input = idEditText.getText().toString().trim();
        if (input.equals("")) {
            ToastUtils.show(this, R.string.empty_edittext_hint);
        } else {
            nurseId = input;
            logIn();
        }
    }


    // 登录函数
    private void logIn() {
        UserInfo userInfo = new UserInfo.Builder()
                .userid(nurseId)
//                .password("1")
                .build();

        LoginService service = HttpUtils.getInstance(this).create(LoginService.class);

        // 先判断用户名是否已经发生切换
        if (!nurseId.equals(sharedHelper.getString(SharedHelper.CURRENT_USER_NAME))) {
            OkHttpClientUtils.getCookieStore(this).removeAll();
        }

        btlogging();
        Observable<NurseInfo> observable = service.login("Login.php", userInfo);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NurseInfo>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.i("onCompleted");
                        onDoNext(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // 登录失败
                        LogUtils.e(e.toString());
                        if (e.toString().equals("retrofit.HttpException: HTTP 404 Not Found")) {

                        }
                        onDoNext(false);
                    }

                    @Override
                    public void onNext(NurseInfo nurseInfo) {
                        if (nurseInfo != null) {
                            // 将数据存储到本地数据库中
                            NurseInfoDao dao = daoUtils.getDaoSession().getNurseInfoDao();
                            try {
                                dao.insertOrReplace(nurseInfo);
                            } catch (Exception e) {
                                dao.update(nurseInfo);
                            }
                            LogUtils.w(nurseInfo.getNurse_id());
                            // 把当前登录成功账号存储到本地中
                            sharedHelper.put(SharedHelper.CURRENT_USER_NAME, nurseInfo.getNurse_id());
                        }
                    }

                    // 显示登录结果
                    private void onDoNext(boolean isSuccess) {
                        btlogged();
                        if (!isSuccess) {
                            ToastUtils.show(LogInActivity.this, R.string.login_fail);
                        } else {
                            startActivity(new Intent(LogInActivity.this, CheckInActivity.class));
                            finish();
                        }
                    }
                });
    }

    // 设置登录按钮状态
    private void btlogging() {
        if (loadingFragment == null)
            loadingFragment = new LoadingFragment();
        getSupportFragmentManager().beginTransaction().add(loadingFragment, "TAG").commit();

        loginBt.setEnabled(false);
        loginBt.setText(R.string.logging);
    }

    // 设置登陆完成状态
    private void btlogged() {
        if(loadingFragment != null)
            loadingFragment.dismiss();
        loginBt.setEnabled(true);
        loginBt.setText(R.string.login);
    }

    public static final int PUBLISH_PROGRESS = 1;  // 从服务器中下载数据库数据
    public static final int DOWNLOAD_DB_POST = 2;  // 从服务器下载数据结果
    public static final int GET_IMAGE_EXCUTE = 3;  // 从服务器下载图片
    public static final int GET_IMAGE_PROGRESS = 4;  // 从服务器下载图片进程
    public static final int POST_EXCUTE = 5;         // 数据加载完成
    public static final int WRONG_NURSE_ID = 6;         // 没有该nurseID

}
