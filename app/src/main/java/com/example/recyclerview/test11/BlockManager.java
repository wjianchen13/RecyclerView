package com.example.recyclerview.test11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 列表级别的拉黑管理类，封装当前 Fragment 的数据和插入位置计算逻辑
 * 拉黑状态由全局单例 BlockedUserManager 维护
 */
public class BlockManager {

    /**
     * 原始完整列表（不会改变，用于恢复位置的参考）
     */
    private final List<User11> allDatas = new ArrayList<>();

    /**
     * 本列表所有用户的 id 集合（init 时构建，不随拉黑变化），用于 O(1) 判断用户是否属于本 Fragment
     */
    private final Set<Integer> allUserIdSet = new HashSet<>();

    /**
     * 初始化原始列表（首次加载或分页追加时调用）
     */
    public void addUsers(List<User11> datas) {
        for (User11 user : datas) {
            add(user);
        }
    }

    /**
     * 添加用户信息
     */
    public void add(User11 user) {
        if (user != null) {
            allDatas.add(user);
            allUserIdSet.add(user.id);
        }
    }

    /**
     * 刷新的时候首先清除所有数据（不清除全局拉黑状态）
     */
    public void clear() {
        allDatas.clear();
        allUserIdSet.clear();
    }

    /**
     * 判断用户是否属于本 Fragment，O(1)
     */
    public boolean containsUser(int userId) {
        return allUserIdSet.contains(userId);
    }

    /**
     * 拉黑用户，委托给全局单例
     */
    public void block(User11 user) {
        BlockedUserManager.getInstance().block(user.id);
    }

    /**
     * 取消拉黑，按 userId 查找
     * 先从全局单例移除，再从 allDatas 中按 id 找出对应 User 返回
     *
     * @return 找到并恢复的用户；若不在黑名单中则返回 null
     */
    public User11 unblock(int userId) {
        if (!BlockedUserManager.getInstance().isBlocked(userId)) {
            return null;
        }
        BlockedUserManager.getInstance().unblock(userId);
        for (User11 user : allDatas) {
            if (user.id == userId) {
                return user;
            }
        }
        return null;
    }

    /**
     * 判断某用户是否在黑名单中，O(1)
     */
    public boolean isBlocked(User11 user) {
        return BlockedUserManager.getInstance().isBlocked(user.id);
    }

    /**
     * 黑名单是否为空
     */
    public boolean isBlockedEmpty() {
        return BlockedUserManager.getInstance().isEmpty();
    }

    /**
     * 计算 user 取消拉黑后应插入 datas 的位置
     *
     * 公式：allDatas 中 user 之前有多少个"未被拉黑"的用户，那个数量就是插入位置
     * 时间复杂度：O(n)，内层 isBlocked() 为 O(1)
     */
    public int calcInsertPosition(User11 user) {
        int allIndex = -1;
        for (int i = 0; i < allDatas.size(); i++) {
            if (allDatas.get(i).id == user.id) {  // 按 id 查找，避免刷新后对象引用失效
                allIndex = i;
                break;
            }
        }
        int insertPos = 0;
        for (int i = 0; i < allIndex; i++) {
            if (!BlockedUserManager.getInstance().isBlocked(allDatas.get(i).id)) {  // O(1)
                insertPos++;
            }
        }
        return insertPos;
    }
}
