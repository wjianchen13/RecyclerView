package com.example.recyclerview.test11;

import android.os.Bundle;
import android.view.View;

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
    // 拉黑管理，封装拉黑相关数据和逻辑
    private BlockManager blockManager = new BlockManager();

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
        blockManager.clear();
        for (int i = 0; i < 1000; i++) {
            User11 user = new User11(i, "用户" + i);
            blockManager.add(user);
            datas.add(user);
        }
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview_vertical);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TestAdapter11(datas);
        recyclerView.setAdapter(adapter);
//        recyclerView.setItemAnimator(null); // 关闭增删动画
    }

    /**
     * 拉黑用户：示例拉黑 id 为 3 的用户
     */
    public void onTest1(View v) {
        blockUser(9);
    }

    private void blockUser(int userId) {
        if(blockManager.containsUser(userId)) {
            BlockUtils.blockUser(this, userId, datas, blockManager, adapter);
        }
    }

    /**
     * 取消拉黑：指定 userId，将该用户恢复到原来的位置
     */
    public void onTest2(View v) {
        // 示例：取消拉黑 id 为 3 的用户
        unblockUser(9);
    }

    private void unblockUser(int userId) {
        if(blockManager.containsUser(userId)) {
            BlockUtils.unblockUser(this, userId, datas, blockManager, adapter);
        }
    }

    /**
     * 拉黑用户：示例拉黑 id 为 3 的用户
     */
    public void onTest3(View v) {
        blockUser(10);
    }

    /**
     * 取消拉黑：指定 userId，将该用户恢复到原来的位置
     */
    public void onTest4(View v) {
        // 示例：取消拉黑 id 为 3 的用户
        unblockUser(10);
    }

    /**
     * 拉黑用户：示例拉黑 id 为 3 的用户
     */
    public void onTest5(View v) {
        blockUser(990);
    }

    /**
     * 取消拉黑：指定 userId，将该用户恢复到原来的位置
     */
    public void onTest6(View v) {
        // 示例：取消拉黑 id 为 3 的用户
        unblockUser(990);
    }

}
