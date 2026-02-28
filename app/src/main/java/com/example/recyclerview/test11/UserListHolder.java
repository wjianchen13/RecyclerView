package com.example.recyclerview.test11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Fragment 数据持有类，封装展示列表和 userId 快速查找 Set。
 *
 * 解决多 Fragment 联动拉黑时的效率问题：
 *   - 用户不在本 Fragment：O(1) 直接返回，不遍历
 *   - 用户在本 Fragment：O(n) 找位置后更新 UI（必要开销，无法避免）
 */
public class UserListHolder {

    // 当前展示的列表，交给 Adapter 使用
    private final List<User11> datas = new ArrayList<>();
    // 本列表中所有用户的 id 集合，用于 O(1) 判断用户是否在本列表中
    private final Set<Integer> userIdSet = new HashSet<>();

    /**
     * 添加一页数据（首次加载或分页追加）
     */
    public void addPage(List<User11> pageData) {
        for (User11 user : pageData) {
            datas.add(user);
            userIdSet.add(user.id);
        }
    }

    /**
     * 收到拉黑通知时调用。
     *
     * @return 被移除的位置（供 notifyItemRemoved 使用）；-1 表示本列表没有该用户
     */
    public int onUserBlocked(int userId) {
        if (!userIdSet.contains(userId)) {
            return -1; // O(1)，本 Fragment 没有该用户，直接返回，不做任何遍历
        }
        // 用户确实在本列表中，找到位置后移除
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).id == userId) {  // O(n)，但只在用户确实存在时才执行
                datas.remove(i);
                userIdSet.remove(userId);
                return i;
            }
        }
        return -1;
    }

    public List<User11> getDatas() {
        return datas;
    }
}
