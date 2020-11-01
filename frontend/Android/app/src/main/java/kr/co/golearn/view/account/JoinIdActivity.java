package kr.co.golearn.view.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.golearn.R;
import kr.co.golearn.util.ProgressLoadingDialog;
import kr.co.golearn.view.main.MainActivity;
import kr.co.golearn.viewmodel.account.AccountViewModel;

public class JoinIdActivity extends AppCompatActivity {
    @BindView(R.id.join_id_txt_info1)
    TextView txtInfo1;
    @BindView(R.id.join_id_txt_info2)
    TextView txtInfo2;
    @BindView(R.id.join_id_txt_info3)
    TextView txtInfo3;
    @BindView(R.id.join_id_layout_input)
    LinearLayout inputLayout;
    @BindView(R.id.join_id_edt_id)
    MaterialEditText editTextId;
    @BindView(R.id.join_id_main_layout)
    ConstraintLayout mainLayout;

    private String userId;
    private AccountViewModel accountViewModel;
    private ProgressLoadingDialog progressLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_id);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        userId = getIntent().getStringExtra("join-id");

        init();
        setAnimation();
    }

    private void init() {
        // 로딩창 객체 생성 및 투명
        progressLoadingDialog = new ProgressLoadingDialog(this);
        progressLoadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ButterKnife.bind(this);
        accountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
        accountViewModel.getSuccessCheckId().observe(this, okId -> {
            progressLoadingDialog.dismiss();
            Intent intent = new Intent(this, JoinPwActivity.class);
            intent.putExtra("join-id", editTextId.getText().toString());
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            finish();
        });
        accountViewModel.getFailCheckId().observe(this, noId -> {
            progressLoadingDialog.dismiss();
            Snackbar.make(mainLayout, "이미 사용중인 아이디네요!", Snackbar.LENGTH_SHORT).setAction("확인", view -> {
            }).show();
        });

        editTextId.setText(userId);
    }

    private void setAnimation() {
        Animation bottomToTop = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
        txtInfo2.startAnimation(bottomToTop);
        Animation topToBottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);
        topToBottom.setStartOffset(300);
        txtInfo1.startAnimation(topToBottom);
        Animation leftToRight = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        leftToRight.setStartOffset(800);
        txtInfo3.startAnimation(leftToRight);
        bottomToTop = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
        bottomToTop.setStartOffset(800);
        inputLayout.startAnimation(bottomToTop);
    }

    @OnClick(R.id.join_id_img_back)
    void clickToBack() {
        finish();
    }

    @OnClick(R.id.join_id_btn_next)
    void clickToNextStep() {
        if (editTextId.getText().length() == 0) {
            editTextId.setError("아이디가 비어있을 수는 없어요!");
            return;
        }
        progressLoadingDialog.show();
        accountViewModel.checkId(editTextId.getText().toString());
    }
}