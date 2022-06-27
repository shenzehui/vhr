package org.javaboy.vhr.controller.system.basic;

import org.javaboy.vhr.model.JobLevel;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author szh
 * @Date 2022/6/21 19:15
 * @PackageName:org.javaboy.vhr.controller.system.basic
 * @ClassName: JobLevelController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {

    @Autowired
    JobLevelService jobLevelService;

    @GetMapping("/")
    public List<JobLevel> getAllJobLevels(){
        return jobLevelService.getAllJobLevels();
    }

    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel){
        Integer i = jobLevelService.addJobLevel(jobLevel);
        if(i>0){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody JobLevel jobLevel){
        Integer i = jobLevelService.updateJobLevel(jobLevel);
        if(i>0){
            return RespBean.ok("修改成功!");
        }
        return RespBean.error("修改失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteJobLevelById(@PathVariable Integer id){
        Integer i =  jobLevelService.deleteJobLevelById(id);
        if(i>0){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @DeleteMapping("/")
    public RespBean deleteJobLevelByIds(Integer [] ids){
         Integer i = jobLevelService.deleteJobLevelByIds(ids);
         if(i>0){
             return RespBean.ok("删除成功!");
         }
         return RespBean.error("删除失败!");
    }
}
