package com.example.recyclerview.test11;

import java.util.HashSet;
import java.util.Set;

/**
 * 全局拉黑管理单例，只存储被拉黑的 userId，供所有 Fragment 共享使用
 */
public class BlockedUserManager {

    private static BlockedUserManager instance;

    /**
     * 被拉黑的 userId 集合，O(1) 查找
     */
    private final Set<Integer> blockedIds = new HashSet<>();

    private BlockedUserManager() {}

    public static BlockedUserManager getInstance() {
        if (instance == null) {
            instance = new BlockedUserManager();
        }
        return instance;
    }

    /**
     * 拉黑用户
     */
    public void block(int userId) {
        blockedIds.add(userId);
    }

    /**
     * 取消拉黑
     */
    public void unblock(int userId) {
        blockedIds.remove(userId);
    }

    /**
     * 判断某用户是否在黑名单中，O(1)
     */
    public boolean isBlocked(int userId) {
        return blockedIds.contains(userId);
    }

    /**
     * 黑名单是否为空
     */
    public boolean isEmpty() {
        return blockedIds.isEmpty();
    }
}
