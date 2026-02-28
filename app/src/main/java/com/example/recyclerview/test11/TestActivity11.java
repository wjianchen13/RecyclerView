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
        List<User11> users = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            users.add(new User11(i, "用户" + i));
        }
        blockManager.init(users);
        datas.addAll(users);
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
        blockUser(3);
    }

    private void blockUser(int userId) {
        // 在当前展示列表中按 userId 找到对应位置
        int pos = -1;
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).id == userId) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            Toast.makeText(this, "列表中找不到该用户", Toast.LENGTH_SHORT).show();
            return;
        }
        User11 user = datas.get(pos);
        blockManager.block(user);
        datas.remove(pos);
        adapter.notifyItemRemoved(pos);
        Toast.makeText(this, "已拉黑：" + user.name, Toast.LENGTH_SHORT).show();
    }

    /**
     * 取消拉黑：指定 userId，将该用户恢复到原来的位置
     */
    public void onTest2(View v) {
        // 示例：取消拉黑 id 为 3 的用户
        unblockUser(3);
    }

    private void unblockUser(int userId) {
        User11 target = blockManager.unblock(userId);
        if (target == null) {
            Toast.makeText(this, "用户不在黑名单中", Toast.LENGTH_SHORT).show();
            return;
        }
        int insertPos = blockManager.calcInsertPosition(target);
        datas.add(insertPos, target);
        adapter.notifyItemInserted(insertPos);
        Toast.makeText(this, "已取消拉黑：" + target.name + "，恢复到位置 " + insertPos, Toast.LENGTH_SHORT).show();
    }

    /**
     * 拉黑用户：示例拉黑 id 为 3 的用户
     */
    public void onTest3(View v) {
        blockUser(990);
    }

    /**
     * 取消拉黑：指定 userId，将该用户恢复到原来的位置
     */
    public void onTest4(View v) {
        // 示例：取消拉黑 id 为 3 的用户
        unblockUser(990);
    }

    /**
     * 拉黑用户：示例拉黑 id 为 3 的用户
     */
    public void onTest5(View v) {
        blockUser(8);
    }

    /**
     * 取消拉黑：指定 userId，将该用户恢复到原来的位置
     */
    public void onTest6(View v) {
        // 示例：取消拉黑 id 为 3 的用户
        unblockUser(8);
    }

}
