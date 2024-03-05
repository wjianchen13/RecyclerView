/*
 * Copyright (C) 2015 Paul Burke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.recyclerview.swipe;

import static androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_IDLE;
import static androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.swipe.base.ItemTouchHelperAdapter;
import com.example.recyclerview.utils.Utils;

/**
 * recyclerview 侧滑
 */
public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    public static final float ALPHA_FULL = 1.0f;

    private Context mContext;
    private final ItemTouchHelperAdapter mAdapter;

    private Drawable imageDrawable;
    private Drawable shareRound;
    private View mView;
    private float dX = 0f;
    private boolean swipeBack = false;
    private boolean isSetTouchListener;
    private RecyclerView.ViewHolder currentItemViewHolder = null;
    private long lastReplyButtonAnimationTime = 0;

    private float replyButtonProgress;
    private boolean isVibrate = false;
    private boolean startTracking = false;

    public ItemTouchHelperCallback(Context context, ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
        imageDrawable = ContextCompat.getDrawable(context, R.drawable.ic_reply_black_24dp);
        shareRound = ContextCompat.getDrawable(context, R.drawable.ic_round_shape);
        this.mContext = context;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        if (swipeBack) {
            swipeBack = false;
            return 0;
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        mView = viewHolder.itemView;
        return ItemTouchHelper.Callback.makeMovementFlags(ACTION_STATE_IDLE, ItemTouchHelper.RIGHT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        currentItemViewHolder = viewHolder;
        if (actionState == ACTION_STATE_SWIPE && !isSetTouchListener) {
            isSetTouchListener = true;
            setTouchListener(recyclerView, viewHolder);
        }

        if (mView.getTranslationX() < Utils.dip2px(mView.getContext(), 130) || dX < this.dX) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            this.dX = dX;
            startTracking = true;
        }

        drawReplyButton(c);
    }

    private void drawReplyButton(Canvas canvas) {
        if (currentItemViewHolder == null) {
            return ;
        }
        float translationX = mView.getTranslationX();
        long newTime = System.currentTimeMillis();
        long dt = Math.min(17, newTime - lastReplyButtonAnimationTime);
        lastReplyButtonAnimationTime = newTime;
        boolean showing = translationX >= Utils.dip2px(mContext, 30);
        if (showing) {
            if (replyButtonProgress < 1.0f) {
                replyButtonProgress += dt / 180.0f;
                if (replyButtonProgress > 1.0f) {
                    replyButtonProgress = 1.0f;
                } else {
                    mView.invalidate();
                }
            }
        } else if (translationX <= 0.0f) {
            replyButtonProgress = 0f;
            startTracking = false;
            isVibrate = false;
        } else {
            if (replyButtonProgress > 0.0f) {
                replyButtonProgress -= dt / 180.0f;
                if (replyButtonProgress < 0.1f) {
                    replyButtonProgress = 0f;
                } else {
                    mView.invalidate();
                }
            }
        }
        int alpha;
        float scale;
        if (showing) {
            if (replyButtonProgress <= 0.8f) {
                scale = 1.2f * (replyButtonProgress / 0.8f);
            } else {
                scale = 1.2f - 0.2f * ((replyButtonProgress - 0.8f) / 0.2f);
            }
            alpha = (int)Math.min(255f, 255 * (replyButtonProgress / 0.8f));
        } else {
            scale = replyButtonProgress;
            alpha = (int)Math.min(255f, 255 * replyButtonProgress);
        }
        shareRound.setAlpha(alpha);

        imageDrawable.setAlpha(alpha);
        if (startTracking) {
            if (!isVibrate && mView.getTranslationX() >= Utils.dip2px(mContext, 100)) {
                mView.performHapticFeedback(
                        HapticFeedbackConstants.KEYBOARD_TAP,
                        HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
                );
                isVibrate = true;
            }
        }

        int x = 0;
        if (mView.getTranslationX() > Utils.dip2px(mContext, 130)) {
            x = Utils.dip2px(mContext, 130) / 2;
        } else {
            x = (int)(mView.getTranslationX() / 2);
        }

        float y = mView.getTop() + mView.getMeasuredHeight() / 2;
        shareRound.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(mContext, R.color.cc4c4c4), PorterDuff.Mode.MULTIPLY));


        shareRound.setBounds(
                (int)(x - Utils.dip2px(mContext, 18) * scale),
                (int)(y - Utils.dip2px(mContext, 18) * scale),
                (int)(x + Utils.dip2px(mContext, 18) * scale),
                (int)(y + Utils.dip2px(mContext, 18) * scale)
        );
        shareRound.draw(canvas);
        imageDrawable.setBounds(
                (int)(x - Utils.dip2px(mContext, 12) * scale),
                (int)(y - Utils.dip2px(mContext, 11) * scale),
                (int)(x + Utils.dip2px(mContext, 12) * scale),
                (int)(y + Utils.dip2px(mContext, 10) * scale)
        );
        imageDrawable.draw(canvas);
        shareRound.setAlpha(255);
        imageDrawable.setAlpha(255);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setTouchListener(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        System.out.println("===============================>>> setTouchListener");
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                swipeBack = event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP;
                if (swipeBack) {
                    if (Math.abs(mView.getTranslationX()) >= Utils.dip2px(mView.getContext(), 100)) {
//                    swipeControllerActions.showReplyUI(viewHolder.adapterPosition)
                    }
                }
                return false;
            }
        });
    }

}
