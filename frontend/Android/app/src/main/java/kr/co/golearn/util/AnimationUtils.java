package kr.co.golearn.util;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

public class AnimationUtils {
    public static void fadeInAddBottomUp(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setFillAfter(true);
        animation.setDuration(500);
        animationSet.addAnimation(animation);

        TranslateAnimation translateAnimation = new TranslateAnimation(0f, 0f, 200f, 0f);
        translateAnimation.setDuration(500);
        translateAnimation.setFillAfter(true);
        animationSet.addAnimation(translateAnimation);

        view.setAnimation(animationSet);
    }

    public static void fadeOut(View view) {
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setFillAfter(true);
        animation.setDuration(500);
        view.startAnimation(animation);
    }

    public static void fadeIn(View view) {
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setFillAfter(true);
        animation.setDuration(500);
        view.startAnimation(animation);
    }
}
