package org.javaboy.vhr.controller.emp;

import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.model.*;
import org.javaboy.vhr.service.*;
import org.javaboy.vhr.uitls.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author szh
 * @Date 2022/6/24 12:39
 * @PackageName:org.javaboy.vhr.controller.emp
 * @ClassName: EmpBasicController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    NationService nationService;

    @Autowired
    PoliticsstatusService politicsstatusService;

    @Autowired
    JobLevelService jobLevelService;

    @Autowired
    PositionService positionService;

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,
                                          Employee employee, Date[] beginDateScope) {
        return employeeService.getEmployeeByPage(page, size, employee,beginDateScope);
    }

    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee) {
        if (employeeService.addEmp(employee) == 1) {
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败");
    }

    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        return nationService.getAllNations();
    }

    @GetMapping("/politicsstatus")
    public List<Politicsstatus> getAllPoliticsstatus() {
        return politicsstatusService.getAllPoliticsstatus();
    }

    @GetMapping("/joblevel")
    public List<JobLevel> getAllJobLevel() {
        return jobLevelService.getAllJobLevels();
    }

    @GetMapping("/position")
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    @GetMapping("/maxWorkID")
    public RespBean maxWordID() {
        RespBean respBean = RespBean.build().setStatus(200).
                setObj(String.format("%08d", employeeService.maxWordID() + 1));
        return respBean;
    }

    @GetMapping("/deps")
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartments();
    }

    @DeleteMapping("/{id}")
    public RespBean deleteEmpByBid(@PathVariable Integer id) {
        if (employeeService.deleteEmpByBid(id) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee) {
        if (employeeService.updateEmp(employee) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData() {
        /*查询所有员工记录*/
        List<Employee> list = (List<Employee>) employeeService.getEmployeeByPage(null, null, null,null).getData();
        return POIUtils.employee2Excel(list);
    }

    @PostMapping("/import")
    public RespBean importData(MultipartFile file) throws IOException {
        List<Employee> list = POIUtils.excel2Employee(file, nationService.getAllNations(), politicsstatusService.getAllPoliticsstatus()
                , departmentService.getAllDepartmentsWithoutChildren(), positionService.getAllPositions(), jobLevelService.getAllJobLevels());
        for (Employee employee : list) {
            System.out.println(employee);
        }
        if (employeeService.addEmps(list) == list.size()) {
            return RespBean.ok("上传成功");
        }
        return RespBean.error("上传失败!");
    }
}
