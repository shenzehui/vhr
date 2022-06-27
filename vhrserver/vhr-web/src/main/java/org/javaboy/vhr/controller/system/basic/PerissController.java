package org.javaboy.vhr.controller.system.basic;

import org.javaboy.vhr.model.Menu;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.Role;
import org.javaboy.vhr.service.MenuService;
import org.javaboy.vhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author szh
 * @Date 2022/6/22 12:59
 * @PackageName:org.javaboy.vhr.controller.system.basic
 * @ClassName: PerissController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PerissController {

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/mids/{rid}")
    public List<Integer> getMidsByRid(@PathVariable Integer rid){
        return menuService.getMidsByRid(rid);
    }

    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid, Integer[] mids){
        if(menuService.updateMenuRole(rid,mids)){
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role){
        Integer i = roleService.addRole(role);
        if(i>0){
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteRoleById(@PathVariable Integer id){
        Integer i =  roleService.deleteRoleById(id);
        if(i>0){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败！");
    }
}
