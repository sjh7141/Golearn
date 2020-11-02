package kr.co.golearn.view.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.golearn.R;
import kr.co.golearn.util.PreferenceManager;
import kr.co.golearn.util.ProgressLoadingDialog;
import kr.co.golearn.view.main.MainActivity;
import kr.co.golearn.viewmodel.account.AccountViewModel;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.account_login_edt_id)
    EditText editTextId;
    @BindView(R.id.account_login_edt_pw)
    EditText editTextPassword;
    @BindView(R.id.account_login_checkbox_save_id)
    CheckBox checkBoxSaveId;
    @BindView(R.id.account_login_checkbox_auto_login)
    CheckBox checkBoxAutoLogin;
    @BindView(R.id.account_login_layout_bottom)
    ConstraintLayout bottomLayout;

    private AccountViewModel accountViewModel;
    private PreferenceManager preferenceManager;
    private ProgressLoadingDialog progressLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        init();
//        actionViewModel();
        clickToJoin();
    }

    private void actionViewModel() {
        accountViewModel.getSaveID(this);
        accountViewModel.getAutoLogin(this);
    }

    private void init() {
        // 로딩창 객체 생성 및 투명
        progressLoadingDialog = new ProgressLoadingDialog(this);
        progressLoadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ButterKnife.bind(this);
        preferenceManager = new PreferenceManager();

        accountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
        accountViewModel.getIsSaveID().observe(this, savedId -> {
            checkBoxSaveId.setChecked(savedId);
            if (savedId) {
                editTextId.setText(preferenceManager.getString(this, PreferenceManager.LOGIN_ID));
            }
        });
        accountViewModel.getIsAutoLogin().observe(this, autoLogin -> {
            checkBoxAutoLogin.setChecked(autoLogin);
            if (autoLogin) {
                login(preferenceManager.getString(this, PreferenceManager.LOGIN_ID), preferenceManager.getString(this, PreferenceManager.LOGIN_PW));
            }

        });
        accountViewModel.getSuccessLogin().observe(this, okLogin -> {
            String id = preferenceManager.getString(this, PreferenceManager.LOGIN_ID);
            String pw = preferenceManager.getString(this, PreferenceManager.LOGIN_PW);
            if (checkBoxSaveId.isChecked()) { // 아이디 저장
                preferenceManager.setBoolean(this, PreferenceManager.IS_SAVED_ID, true);
                preferenceManager.setString(this, PreferenceManager.LOGIN_ID, editTextId.getText().toString());
            } else {
                preferenceManager.setBoolean(this, PreferenceManager.IS_SAVED_ID, false);
            }
            if (checkBoxAutoLogin.isChecked()) { // 자동 로그인
                preferenceManager.setBoolean(this, PreferenceManager.IS_AUTO_LOGIN, true);
                preferenceManager.setString(this, PreferenceManager.LOGIN_ID, editTextId.getText().toString());
                if (pw.equals("NO_DATA")) {
                    preferenceManager.setString(this, PreferenceManager.LOGIN_PW, editTextPassword.getText().toString());
                } else {
                    preferenceManager.setString(this, PreferenceManager.LOGIN_PW, pw);
                }
            } else {
                preferenceManager.setBoolean(this, PreferenceManager.IS_AUTO_LOGIN, false);
            }
            progressLoadingDialog.dismiss();
            startActivity(new Intent(this, MainActivity.class));
//            finish();
        });
        accountViewModel.getFailLogin().observe(this, noLogin -> {
            Snackbar.make(bottomLayout, "아이디 또는 비밀번호가 일치하지 않습니다.", Snackbar.LENGTH_SHORT).show();
            progressLoadingDialog.dismiss();
        });
    }

    void login(String id, String password) {
        progressLoadingDialog.show();
        accountViewModel.login(this, id, password);
    }

    @OnClick(R.id.account_login_btn_login)
    void clickToLogin() {
        login(editTextId.getText().toString(), editTextPassword.getText().toString());
    }

    @OnClick(R.id.account_login_txt_join)
    void clickToJoin(){
        startActivity(new Intent(this, JoinIdActivity.class));
    }
}