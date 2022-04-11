package com.example.recyclerview.refresh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

/**
 * Created by Administrator on 2018/4/27.
 */

public class MySmartRefreshLayout extends SmartRefreshLayout {

    public MySmartRefreshLayout(Context context) {
        super(context);
    }

    public MySmartRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySmartRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 自动加载
     */
    @Override
    public boolean autoLoadmore(int delayed, final int duration, final float dragrate) {
        mEnableLoadmore = true;
        if (mState == RefreshState.None && (isEnableLoadmore() && !mLoadmoreFinished)) {
            if (reboundAnimator != null) {
                reboundAnimator.cancel();
            }
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    reboundAnimator = ValueAnimator.ofInt(mSpinner, -(int) (mFooterHeight * dragrate));
                    reboundAnimator.setDuration(duration);
                    reboundAnimator.setInterpolator(new DecelerateInterpolator());
                    reboundAnimator.addUpdateListener(new AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            moveSpinner((int) animation.getAnimatedValue(), false);
                        }
                    });
                    reboundAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            mLastTouchX = getMeasuredWidth() / 2;
                            setStatePullUpToLoad();
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            reboundAnimator = null;
                            if (mState != RefreshState.ReleaseToLoad) {
                                setStateReleaseToLoad();
                            }
                            if (mEnableAutoLoadmore) {
                                mEnableAutoLoadmore = false;
                                overSpinner();
                                mEnableAutoLoadmore = true;
                                mEnableLoadmore = false;
                            } else {
                                overSpinner();
                            }
                        }
                    });
                    reboundAnimator.start();
                }
            };
            if (delayed > 0) {
                reboundAnimator = new ValueAnimator();
                postDelayed(runnable, delayed);
            } else {
                runnable.run();
            }
            return true;
        } else {
            return false;
        }
    }

}
