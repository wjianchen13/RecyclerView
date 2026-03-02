package com.example.recyclerview.test11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 列表级别的拉黑管理类，封装当前 Fragment 的数据和插入位置计算逻辑
 *
 * 拉黑状态双层维护：
 *  - localBlockedIds：本 Fragment 私有，所有判断/计算都走这里
 *  - BlockedUserManager：全局单例，block/unblock 时同步更新
 *
 * 原因：外部业务可能先清除全局拉黑再通知 Fragment，
 *       此时全局单例已无数据，必须依赖本地副本才能正确处理
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
     * 本 Fragment 本地维护的拉黑 id 集合，O(1) 查找
     * 解决：外部业务先清全局拉黑再通知 Fragment 时，全局单例已无数据的问题
     */
    private final Set<Integer> localBlockedIds = new HashSet<>();

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
     * 刷新的时候首先清除所有数据（不清除拉黑状态）
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
     * 拉黑用户
     * 同时写入本地副本和全局单例
     */
    public void block(User11 user) {
        localBlockedIds.add(user.id);
        BlockedUserManager.getInstance().block(user.id);
    }

    /**
     * 取消拉黑，按 userId 查找
     * 判断和移除都走本地副本，全局单例同步更新（外部已清除则无副作用）
     *
     * @return 找到并恢复的用户；若本地黑名单中不存在则返回 null
     */
    public User11 unblock(int userId) {
        if (!localBlockedIds.contains(userId)) {
            return null;
        }
        localBlockedIds.remove(userId);
        BlockedUserManager.getInstance().unblock(userId); // 同步全局，若外部已清除则无副作用
        for (User11 user : allDatas) {
            if (user.id == userId) {
                return user;
            }
        }
        return null;
    }

    /**
     * 判断某用户是否在本 Fragment 黑名单中，O(1)
     */
    public boolean isBlocked(User11 user) {
        return localBlockedIds.contains(user.id);
    }

    /**
     * 本 Fragment 黑名单是否为空
     */
    public boolean isBlockedEmpty() {
        return localBlockedIds.isEmpty();
    }

    /**
     * 计算 user 取消拉黑后应插入 datas 的位置
     *
     * 公式：allDatas 中 user 之前有多少个"未被拉黑"的用户，那个数量就是插入位置
     * 时间复杂度：O(n)，内层 contains() 为 O(1)
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
            if (!localBlockedIds.contains(allDatas.get(i).id)) {  // O(1)
                insertPos++;
            }
        }
        return insertPos;
    }
}
