package kr.co.golearn.adaptor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import kr.co.golearn.view.main.channel.PagerCourseFragment;
import kr.co.golearn.view.main.channel.PagerHomeFragment;

public class ChannelPagerAdapter extends FragmentStateAdapter {

    public ChannelPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PagerHomeFragment();
            case 1:
                return new PagerCourseFragment();
        }
        return new PagerHomeFragment();
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
