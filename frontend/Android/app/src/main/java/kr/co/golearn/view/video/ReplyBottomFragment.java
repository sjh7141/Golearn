package kr.co.golearn.view.video;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.golearn.R;
import kr.co.golearn.util.PreferenceManager;
import kr.co.golearn.viewmodel.TextViewModel;

public class ReplyBottomFragment extends BottomSheetDialogFragment {
    @BindView(R.id.bottom_reply_edt_comment)
    EditText edtComment;
    @BindView(R.id.bottom_reply_img_profile)
    ImageView profile;
    @BindView(R.id.bottom_reply_btn_ok)
    ImageView btnOk;

    private View view;
    private TextViewModel textViewModel;

    public ReplyBottomFragment(TextViewModel comment) {
        this.textViewModel = comment;
    }

    public static ReplyBottomFragment newInstance(TextViewModel comment) {
        return new ReplyBottomFragment(comment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bottom_reply, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
    }

    private void init() {
        Glide.with(this)
                .load(new PreferenceManager().getString(this.getContext(), PreferenceManager.USER_PROFILE))
                .circleCrop()
                .into(profile);
        edtComment.requestFocus();
        btnOk.setVisibility(View.INVISIBLE);

        edtComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력되는 텍스트에 변화가 있을 때
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    btnOk.setVisibility(View.INVISIBLE);
                } else {
                    btnOk.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력하기 전에
            }
        });
    }

    @OnClick(R.id.bottom_reply_btn_ok)
    void clickToOk() {
        String text = edtComment.getText().toString();
        textViewModel.setReplyText(text);
        this.dismiss();
    }
}