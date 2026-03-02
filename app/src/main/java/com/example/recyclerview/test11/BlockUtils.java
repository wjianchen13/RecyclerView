package com.example.recyclerview.test11;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * 拉黑房间相关操作
 */
public class BlockUtils {

    /**
     * 拉黑某个用户
     * @param userId 用户id
     * @param datas 列表中正在显示的数据
     */
    public static void blockUser(Context context, int userId, List<User11> datas, BlockManager blockManager, RecyclerView.Adapter adapter) {
        if(context == null) {
            return ;
        }
        if(datas == null) {
            Toast.makeText(context, "列表没有数据", Toast.LENGTH_SHORT).show();
            return ;
        }
        if(blockManager == null) {
            Toast.makeText(context, "拉黑管理为空", Toast.LENGTH_SHORT).show();
            return ;
        }
        if(adapter == null) {
            Toast.makeText(context, "适配器为空", Toast.LENGTH_SHORT).show();
            return ;
        }
        // 在当前展示列表中按 userId 找到对应位置
        int pos = -1;
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).id == userId) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            Toast.makeText(context, "列表中找不到该用户", Toast.LENGTH_SHORT).show();
            return;
        }
        User11 user = datas.get(pos);
        blockManager.block(user);
        datas.remove(pos);
        adapter.notifyItemRemoved(pos);
        Toast.makeText(context, "已拉黑：" + user.name, Toast.LENGTH_SHORT).show();
    }

    /**
     * 取消拉黑某个用户
     * @param userId 用户id
     * @param datas 列表中正在显示的数据
     */
    public static void unblockUser(Context context, int userId, List<User11> datas, BlockManager blockManager, RecyclerView.Adapter adapter) {
        if(context == null) {
            return ;
        }
        if(datas == null) {
            Toast.makeText(context, "列表没有数据", Toast.LENGTH_SHORT).show();
            return ;
        }
        if(blockManager == null) {
            Toast.makeText(context, "拉黑管理为空", Toast.LENGTH_SHORT).show();
            return ;
        }
        if(adapter == null) {
            Toast.makeText(context, "适配器为空", Toast.LENGTH_SHORT).show();
            return ;
        }
        User11 target = blockManager.unblock(userId);
        if (target == null) {
            Toast.makeText(context, "用户不在黑名单中", Toast.LENGTH_SHORT).show();
            return;
        }
        int insertPos = blockManager.calcInsertPosition(target);
        datas.add(insertPos, target);
        adapter.notifyItemInserted(insertPos);
        Toast.makeText(context, "已取消拉黑：" + target.name + "，恢复到位置 " + insertPos, Toast.LENGTH_SHORT).show();
    }

}
