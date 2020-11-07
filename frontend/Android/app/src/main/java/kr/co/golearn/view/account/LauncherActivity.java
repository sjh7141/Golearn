package kr.co.golearn.view.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import kr.co.golearn.R;
import kr.co.golearn.domain.Video;
import kr.co.golearn.view.main.MainActivity;
import kr.co.golearn.view.main.channel.ChannelActivity;
import kr.co.golearn.view.video.VideoActivity;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_launcher);

        loading();
    }

    private void loading() {
        Intent intent = new Intent(this, LoginActivity.class);
        new Handler().postDelayed(() -> {
            startActivity(intent);
            finish();
        }, 100);
    }
}