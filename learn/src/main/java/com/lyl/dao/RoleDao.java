package com.lyl.dao;

import com.lyl.entity.Role;
import java.util.List;

public interface RoleDao {
    /**
     * 返回所有角色
     * @return
     */
    List<Role> findAll();
}
