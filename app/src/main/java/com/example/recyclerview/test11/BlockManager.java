package com.example.recyclerview.test11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拉黑管理类，封装拉黑/取消拉黑的数据和查找逻辑
 */
public class BlockManager {

    // 原始完整列表（不会改变，用于恢复位置的参考）
    private final List<User11> allDatas = new ArrayList<>();
    // userId -> User11，同时承担两个职责：
    //   1. unblock() 时按 id O(1) 取出对象
    //   2. calcInsertPosition() 时 O(1) 判断某用户是否被拉黑
    private final Map<Integer, User11> blockedMap = new HashMap<>();

    /**
     * 初始化原始列表（首次加载或分页追加时调用）
     */
    public void init(List<User11> datas) {
        allDatas.addAll(datas);
    }

    /**
     * 拉黑用户，O(1)
     */
    public void block(User11 user) {
        blockedMap.put(user.id, user);
    }

    /**
     * 取消拉黑，按 userId 查找，O(1)
     *
     * @return 找到并移除的用户；若不在黑名单中则返回 null
     */
    public User11 unblock(int userId) {
        return blockedMap.remove(userId);
    }

    /**
     * 判断某用户是否在黑名单中，O(1)
     */
    public boolean isBlocked(User11 user) {
        return blockedMap.containsKey(user.id);
    }

    /**
     * 黑名单是否为空
     */
    public boolean isBlockedEmpty() {
        return blockedMap.isEmpty();
    }

    /**
     * 计算 user 取消拉黑后应插入 datas 的位置
     *
     * 公式：allDatas 中 user 之前有多少个"未被拉黑"的用户，那个数量就是插入位置
     * 时间复杂度：O(n)，内层 blockedMap.containsKey() 为 O(1)
     */
    public int calcInsertPosition(User11 user) {
        int allIndex = allDatas.indexOf(user);  // O(n)
        int insertPos = 0;
        for (int i = 0; i < allIndex; i++) {
            if (!blockedMap.containsKey(allDatas.get(i).id)) {  // O(1)
                insertPos++;
            }
        }
        return insertPos;
    }
}
