package org.javaboy.vhr.controller.system.basic;

import org.javaboy.vhr.model.Position;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author szh
 * @Date 2022/6/21 10:14
 * @PackageName:org.javaboy.vhr.controller.system.basic
 * @ClassName: PositionController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    PositionService positionService;

    @GetMapping("/")
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position) {
        Integer i = positionService.addPosition(position);
        if (i > 0) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/")
    public RespBean updatePositions(@RequestBody Position position) {
        Integer i = positionService.updatePositions(position);
        if (i > 0) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败");

    }

    @DeleteMapping("/{id}")
    public RespBean deletePositionById(@PathVariable Integer id) {
        Integer i = positionService.deletePositionById(id);
        if (i > 0) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败");

    }

    @DeleteMapping("/")
    public RespBean deletePositionByIds(Integer[] ids) {
        Integer i = positionService.deletePositionByIds(ids);
        if (i == ids.length) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败");
    }
}
