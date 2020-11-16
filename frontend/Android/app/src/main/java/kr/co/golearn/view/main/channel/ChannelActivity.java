package kr.co.golearn.view.main.channel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.golearn.R;
import kr.co.golearn.adaptor.ChannelPagerAdapter;

public class ChannelActivity extends AppCompatActivity {
    @BindView(R.id.channel_tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.channel_viewpager)
    ViewPager2 viewPager;

    private long mbrNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        mbrNo = getIntent().getLongExtra("mbrNo", 0L);

        init();
        setAdapter();
    }

    private void setAdapter() {
        ChannelPagerAdapter adapter = new ChannelPagerAdapter(this, mbrNo);
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("홈");
                    break;
                case 1:
                    tab.setText("코스");
                    break;
            }
        }).attach();
    }

    private void init() {
        ButterKnife.bind(this);
    }


    @OnClick(R.id.channel_btn_back)
    void clickToBack(){
        finish();
    }
}