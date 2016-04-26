package hust.nursenfcclient;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.SubscriptSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.squareup.okhttp.internal.http.RouteSelector;

import junit.framework.Test;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

import hust.nursenfcclient.utils.LogUtils;
import nurse_db.NurseInfo;

/**
 * Created by admin on 2015/11/20.
 */
public class TestActivity extends FragmentActivity implements View.OnClickListener{


    private NumberProgressBar download_progress_bar;

    private MyHandler mHandler;

    private Button MyClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

        mHandler = new MyHandler(this);

        Message msg = mHandler.obtainMessage(ACTION_1);
        msg.obj = new NurseInfo("100000");
        mHandler.sendMessage(msg);


        SpannableString text = new SpannableString("Hello1");
        text.setSpan(new SubscriptSpan(), text.length() - 2, text.length() - 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        MyClick = (Button) findViewById(R.id.MyClick);
        MyClick.setOnClickListener(this);

        MyClick.setText(text);

//        long max = Runtime.getRuntime().maxMemory() / 1024 / 1024;
//
//
//        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        int max_memory = am.getLargeMemoryClass();
//        LogUtils.i("max memory == " + max_memory);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MyClick:
//                TestFragment fragment = new TestFragment();
//                getSupportFragmentManager().beginTransaction().add(fragment, "fragment").commit();
//                fragment.show(getSupportFragmentManager(), "fragment");



                LogUtils.i("MyClick");

                break;
        }
    }

    private class DownloadAsyncTask extends AsyncTask<Void, Integer, Void> {
        private int count = 0;
        @Override
        protected Void doInBackground(Void... params) {
            try {
                while (count < 100) {
                    Thread.sleep(50);
                    publishProgress(++count);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            download_progress_bar.setProgress(values[0]);


        }
    }

    private static final int ACTION_1 = 1;

    private static class MyHandler extends Handler {
        WeakReference<Activity> act;

        public MyHandler(Activity activity) {
            act = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ((TestActivity)act.get()).handleMessage(msg);
        }
    }

    private void handleMessage(Message msg) {
        switch (msg.what) {
            case ACTION_1:
//                NurseInfo info = (NurseInfo) msg.obj;
                Class<NurseInfo> clazz = NurseInfo.class;
                NurseInfo info = clazz.cast(msg.obj);
                LogUtils.w("NurseId == " + info.getNurse_id());
                break;
        }
    }

    private <T> T $(int resId) {
        return (T) findViewById(resId);
    }
}
