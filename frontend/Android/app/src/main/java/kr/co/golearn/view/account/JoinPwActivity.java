package kr.co.golearn.view.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.golearn.R;
import kr.co.golearn.viewmodel.account.AccountViewModel;

public class JoinPwActivity extends AppCompatActivity {

    @BindView(R.id.join_pw_edt_pw)
    TextInputEditText editTextPw;

    private String userId;
    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_pw);

        userId = getIntent().getStringExtra("join-id");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        init();
    }

    private void init() {
        ButterKnife.bind(this);
        accountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
        accountViewModel.getResponseJoin().observe(this, responseJoin -> {
            switch (responseJoin){
                case "등록 완료":
                    Toast.makeText(this, "가입을 축하합니다!", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case "SQL Error":
                    Toast.makeText(this, "오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        });
    }


    @OnClick(R.id.join_pw_img_back)
    void clickToBack(){
        Intent intent = new Intent(this, JoinIdActivity.class);
        intent.putExtra("join-id", userId);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.join_pw_btn_next)
    void clickToNext(){
        accountViewModel.join(userId, editTextPw.getText().toString());
    }
}