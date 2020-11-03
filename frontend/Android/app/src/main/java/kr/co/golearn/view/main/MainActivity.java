package kr.co.golearn.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.golearn.R;
import kr.co.golearn.view.main.article.ArticleFragment;
import kr.co.golearn.view.main.channel.ChannelActivity;
import kr.co.golearn.view.main.home.HomeFragment;
import kr.co.golearn.view.main.subscription.SubscriptionFragment;
import kr.co.golearn.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_bottom_navigation)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.main_profile)
    ImageView imgProfile;

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private SubscriptionFragment subscriptionFragment;
    private ArticleFragment articleFragment;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        actionViewModel();
    }

    private void actionViewModel() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mainViewModel.getMember().observe(this, member -> {
            Glide.with(this).load("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSyTP4OcpyrXA6j1BDWscLhVaZFCjcH5tA6sA&usqp=CAU")
                    .placeholder(R.drawable.account_login_auto_check_24dp)
                    .error(R.drawable.account_login_auto_check_24dp)
                    .circleCrop()
                    .into(imgProfile);
        });

        mainViewModel.getMember(this);
    }

    private void init() {
        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.main_frame, homeFragment).commit();
    }

    @OnClick(R.id.page_home)
    public void clickToHome(){
        if(homeFragment == null){
            homeFragment = new HomeFragment();
            fragmentManager.beginTransaction().add(R.id.main_frame, homeFragment).commit();
        }

        if(homeFragment != null){
            fragmentManager.beginTransaction().show(homeFragment).commit();
        }
        if(subscriptionFragment !=null){
            fragmentManager.beginTransaction().hide(subscriptionFragment).commit();
        }
        if(articleFragment != null){
            fragmentManager.beginTransaction().hide(articleFragment).commit();
        }
    }
    @OnClick(R.id.page_subscription)
    public void clickToSubscription(){
        if(subscriptionFragment == null){
            subscriptionFragment = new SubscriptionFragment();
            fragmentManager.beginTransaction().add(R.id.main_frame, subscriptionFragment).commit();
        }

        if(homeFragment != null){
            fragmentManager.beginTransaction().hide(homeFragment).commit();
        }
        if(subscriptionFragment !=null){
            fragmentManager.beginTransaction().show(subscriptionFragment).commit();
        }
        if(articleFragment != null){
            fragmentManager.beginTransaction().hide(articleFragment).commit();
        }
    }
    @OnClick(R.id.page_article)
    public void clickToArticle(){
        if(articleFragment == null){
            articleFragment = new ArticleFragment();
            fragmentManager.beginTransaction().add(R.id.main_frame, articleFragment).commit();
        }

        if(homeFragment != null){
            fragmentManager.beginTransaction().hide(homeFragment).commit();
        }
        if(subscriptionFragment !=null){
            fragmentManager.beginTransaction().hide(subscriptionFragment).commit();
        }
        if(articleFragment != null){
            fragmentManager.beginTransaction().show(articleFragment).commit();
        }
    }

    @OnClick(R.id.main_profile)
    public void clickToProfile(){
        startActivity(new Intent(this, ChannelActivity.class));
    }
}