package kr.co.golearn.view.main.channel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.golearn.R;

public class PagerCourseFragment extends Fragment {

    @BindView(R.id.textView8)
    TextView textView;

    private int a = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager_course, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.asdfasdf)
    void asdf(){
        textView.setText(a++ + " ");
    }
}
