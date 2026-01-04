package com.example.recyclerview.message1;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// 游戏使用
public class MessageLayoutManager1 extends LinearLayoutManager {
	private OnScrollStateChangedDinoListener dinoListener;

	public void setDinoListener(OnScrollStateChangedDinoListener dinoListener){
		this.dinoListener = dinoListener;
	}

	/**
	 * interface
	 * 	=0 表示停止滑动的状态 SCROLL_STATE_IDLE
	 =1表示正在滚动，用户手指在屏幕上 SCROLL_STATE_TOUCH_SCROLL
	 =2表示正在滑动。用户手指已经离开屏幕 SCROLL_STATE_FLING
	 */
	public interface OnScrollStateChangedDinoListener {
		void onScrollStateChanged(int state);
	}

	@Override
	public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
		try {
			super.onLayoutChildren(recycler, state);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onScrollStateChanged(int state) {
		super.onScrollStateChanged(state);
		if(this.dinoListener != null){
			this.dinoListener.onScrollStateChanged(state);
		}
	}

	public MessageLayoutManager1(Context context) {
		super(context);
	}
}
