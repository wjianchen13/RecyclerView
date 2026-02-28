package com.example.recyclerview.test11;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试删除数据，恢复数据情况
 * 演示拉黑用户（从列表移除）和取消拉黑（恢复到原始位置）
 */
public class TestActivity11 extends AppCompatActivity {

    // 当前展示的列表
    private List<User11> datas = new ArrayList<>();
    // 原始完整列表（不会改变，用于恢复位置的参考）
    private List<User11> allDatas = new ArrayList<>();
    // 被拉黑的用户（按拉黑顺序记录，取消拉黑时 LIFO）
    private List<User11> blockedDatas = new ArrayList<>();

    private TestAdapter11 adapter;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test11);
        initData();
        initRecyclerView();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            allDatas.add(new User11(i, "用户" + i));
        }
        datas.addAll(allDatas);
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview_vertical);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TestAdapter11(datas);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 拉黑用户：移除下标为 3 的用户
     */
    public void onTest1(View v) {
        if (datas.isEmpty()) {
            Toast.makeText(this, "没有可拉黑的用户", Toast.LENGTH_SHORT).show();
            return;
        }
        int pos = Math.min(3, datas.size() - 1);
        User11 user = datas.get(pos);
        blockedDatas.add(user);
        datas.remove(pos);
        adapter.notifyItemRemoved(pos);
        Toast.makeText(this, "已拉黑：" + user.name, Toast.LENGTH_SHORT).show();
    }

    /**
     * 取消拉黑：将最近被拉黑的用户恢复到原来的位置
     */
    public void onTest2(View v) {
        if (blockedDatas.isEmpty()) {
            Toast.makeText(this, "没有被拉黑的用户", Toast.LENGTH_SHORT).show();
            return;
        }
        User11 user = blockedDatas.remove(blockedDatas.size() - 1);
        int insertPos = calcInsertPosition(user);
        datas.add(insertPos, user);
        adapter.notifyItemInserted(insertPos);
        Toast.makeText(this, "已取消拉黑：" + user.name + "，恢复到位置 " + insertPos, Toast.LENGTH_SHORT).show();
    }

    /**
     * 根据 allDatas 的顺序，计算 user 应插入 datas 的位置。
     * 逻辑：找出 allDatas 中 user 之前有多少个"未被拉黑"的用户，那个数量就是插入位置。
     */
    private int calcInsertPosition(User11 user) {
        int allIndex = allDatas.indexOf(user);
        int insertPos = 0;
        for (int i = 0; i < allIndex; i++) {
            if (!blockedDatas.contains(allDatas.get(i))) {
                insertPos++;
            }
        }
        return insertPos;
    }

}
