package hust.nursenfcclient.login;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hust.nursenfcclient.R;

/**
 * Created by admin on 2016/4/25.
 */
public class LoadingFragment extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new LoadingDialog(getActivity());
    }

    public class LoadingDialog extends Dialog {
        public LoadingDialog(Context context) {
            super(context, R.style.LoadingDialog);
        }

        public LoadingDialog(Context context, int theme) {
            super(context, theme);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.loadingview_layout);
        }
    }
}
