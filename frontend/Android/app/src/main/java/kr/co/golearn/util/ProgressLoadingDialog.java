package kr.co.golearn.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import androidx.annotation.NonNull;

import kr.co.golearn.R;

public class ProgressLoadingDialog extends Dialog {

    public ProgressLoadingDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_progress);
    }
}
