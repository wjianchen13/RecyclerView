package com.example.recyclerview.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.recyclerview.R;

import java.util.List;

public class UserBadgeView extends FlowLayout{

    protected Context mContext;
    private MarqueeTextView mtvTest;
    private TextView tvTest1;
    private TextView tvTest2;
    private TextView tvTest3;
    private TextView tvTest4;
    private TextView tvTest5;
    private TextView tvTest6;
    private TextView tvTest7;
    private TextView tvTest8;
    private TextView tvTest9;
    private TextView tvTest10;
    private TextView tvTest11;
    private TextView tvTest12;
    private TextView tvTest13;
    private TextView tvTest14;
    private TextView tvTest15;

    public UserBadgeView(Context context) {
        super(context);
        initView(context);
    }

    public UserBadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_user_badge, this);
        mtvTest = findViewById(R.id.mtv_room_name);
        tvTest1 = findViewById(R.id.tv_test1);
        tvTest2 = findViewById(R.id.tv_test2);
        tvTest3 = findViewById(R.id.tv_test3);
        tvTest4 = findViewById(R.id.tv_test4);
        tvTest5 = findViewById(R.id.tv_test5);
        tvTest6 = findViewById(R.id.tv_test6);
        tvTest7 = findViewById(R.id.tv_test7);
        tvTest8 = findViewById(R.id.tv_test8);
        tvTest9 = findViewById(R.id.tv_test9);
        tvTest10 = findViewById(R.id.tv_test10);
        tvTest11 = findViewById(R.id.tv_test11);
        tvTest12 = findViewById(R.id.tv_test12);
        tvTest13 = findViewById(R.id.tv_test13);
        tvTest14 = findViewById(R.id.tv_test14);
        tvTest15 = findViewById(R.id.tv_test15);

    }

    public void setView(FlowlayoutBean bean) {
        resetView();
        List<FlowlayoutItemBean> itemBeans = bean.getList();
        for(int i = 0; i <itemBeans.size(); i ++) {
            initView(itemBeans.get(i));
        }
        mtvTest.setContent("aksdfjkasdfjkasjkdfjkasdkf");
    }

    private void resetView() {
        tvTest1.setVisibility(View.GONE);
        tvTest2.setVisibility(View.GONE);
        tvTest3.setVisibility(View.GONE);
        tvTest4.setVisibility(View.GONE);
        tvTest5.setVisibility(View.GONE);
        tvTest6.setVisibility(View.GONE);
        tvTest7.setVisibility(View.GONE);
        tvTest8.setVisibility(View.GONE);
        tvTest9.setVisibility(View.GONE);
        tvTest10.setVisibility(View.GONE);
        tvTest11.setVisibility(View.GONE);
        tvTest12.setVisibility(View.GONE);
        tvTest13.setVisibility(View.GONE);
        tvTest14.setVisibility(View.GONE);
        tvTest15.setVisibility(View.GONE);

    }

    private void initView(FlowlayoutItemBean itemBean) {
        if(itemBean != null) {
            int id = itemBean.getId();
            switch (id) {
                case FlowlayoutItemBean.TYPE_1:
                    tvTest1.setText(itemBean.getContent());
                    tvTest1.setVisibility(View.VISIBLE);
                    break;
                case FlowlayoutItemBean.TYPE_2:
                    tvTest2.setText(itemBean.getContent());
                    tvTest2.setVisibility(View.VISIBLE);
                    break;
                case FlowlayoutItemBean.TYPE_3:
                    tvTest3.setText(itemBean.getContent());
                    tvTest3.setVisibility(View.VISIBLE);
                    break;
                case FlowlayoutItemBean.TYPE_4:
//                    tvTest4.setText(itemBean.getContent());
//                    tvTest4.setVisibility(View.VISIBLE);
                    break;
                case FlowlayoutItemBean.TYPE_5:
                    tvTest5.setText(itemBean.getContent());
                    tvTest5.setVisibility(View.VISIBLE);
                    break;
                case FlowlayoutItemBean.TYPE_6:
                    tvTest6.setText(itemBean.getContent());
                    tvTest6.setVisibility(View.VISIBLE);
                    break;
                case FlowlayoutItemBean.TYPE_7:
                    tvTest7.setText(itemBean.getContent());
                    tvTest7.setVisibility(View.VISIBLE);
                    break;
                case FlowlayoutItemBean.TYPE_8:
                    tvTest8.setText(itemBean.getContent());
                    tvTest8.setVisibility(View.VISIBLE);
                    break;
                case FlowlayoutItemBean.TYPE_9:
                    tvTest9.setText(itemBean.getContent());
                    tvTest9.setVisibility(View.VISIBLE);
                    break;
                case FlowlayoutItemBean.TYPE_10:
                    tvTest10.setText(itemBean.getContent());
                    tvTest10.setVisibility(View.VISIBLE);
                    break;
                case FlowlayoutItemBean.TYPE_11:
                    tvTest11.setText(itemBean.getContent());
                    tvTest11.setVisibility(View.VISIBLE);
                    break;
                case FlowlayoutItemBean.TYPE_12:
                    tvTest12.setText(itemBean.getContent());
                    tvTest12.setVisibility(View.VISIBLE);
                    break;
                case FlowlayoutItemBean.TYPE_13:
                    tvTest13.setText(itemBean.getContent());
                    tvTest13.setVisibility(View.VISIBLE);
                    break;
                case FlowlayoutItemBean.TYPE_14:
                    tvTest14.setText(itemBean.getContent());
                    tvTest14.setVisibility(View.VISIBLE);
                    break;
                case FlowlayoutItemBean.TYPE_15:
                    tvTest15.setText(itemBean.getContent());
                    tvTest15.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

}
