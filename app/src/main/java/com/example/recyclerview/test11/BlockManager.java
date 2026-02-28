package com.example.recyclerview.test11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 拉黑管理类，封装拉黑/取消拉黑的数据和查找逻辑
 */
public class BlockManager {

    // 原始完整列表（不会改变，用于恢复位置的参考）
    private final List<User11> allDatas = new ArrayList<>();
    // 被拉黑的用户列表，支持按任意指定用户移除
    private final List<User11> blockedList = new ArrayList<>();
    // 被拉黑用户的集合，用于 O(1) 的 contains 查询
    private final Set<User11> blockedSet = new HashSet<>();

    /**
     * 初始化原始列表（首次加载或分页追加时调用）
     */
    public void init(List<User11> datas) {
        allDatas.addAll(datas);
    }

    /**
     * 拉黑用户
     */
    public void block(User11 user) {
        if (!blockedSet.contains(user)) {
            blockedList.add(user);
            blockedSet.add(user);
        }
    }

    /**
     * 取消拉黑，按 userId 查找
     *
     * @return 找到并移除的用户；若不在黑名单中则返回 null
     */
    public User11 unblock(int userId) {
        User11 target = null;
        for (User11 u : blockedList) {
            if (u.id == userId) {
                target = u;
                break;
            }
        }
        if (target != null) {
            blockedList.remove(target);
            blockedSet.remove(target);
        }
        return target;
    }

    /**
     * 判断某用户是否在黑名单中，O(1)
     */
    public boolean isBlocked(User11 user) {
        return blockedSet.contains(user);
    }

    /**
     * 黑名单是否为空
     */
    public boolean isBlockedEmpty() {
        return blockedList.isEmpty();
    }

    /**
     * 计算 user 取消拉黑后应插入 datas 的位置
     *
     * 公式：allDatas 中 user 之前有多少个"未被拉黑"的用户，那个数量就是插入位置
     * 时间复杂度：O(n)，内层 blockedSet.contains() 为 O(1)
     */
    public int calcInsertPosition(User11 user) {
        int allIndex = allDatas.indexOf(user);  // O(n)
        int insertPos = 0;
        for (int i = 0; i < allIndex; i++) {
            if (!blockedSet.contains(allDatas.get(i))) {  // O(1)
                insertPos++;
            }
        }
        return insertPos;
    }
}
