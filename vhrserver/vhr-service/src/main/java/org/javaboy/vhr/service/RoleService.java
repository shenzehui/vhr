package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.RoleMapper;
import org.javaboy.vhr.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author szh
 * @Date 2022/6/22 13:01
 * @PackageName:org.javaboy.vhr.service
 * @ClassName: RoleService
 * @Description: TODO
 * @Version 1.0
 */
@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    public Integer addRole(Role role) {
        if(!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        return roleMapper.insertSelective(role);
    }

    public Integer deleteRoleById(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }
}
