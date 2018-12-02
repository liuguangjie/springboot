package com.springboot.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.springboot.entity.Department;
import com.springboot.entity.Result;
import com.springboot.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据库增加删除测试
 */
//@RestController
@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;
    //@GetMapping("/list") //json使用
    public Object listDepts() {
        //分页调用
        Page<Department> page = PageHelper.startPage(1, 6);
        Result result = new Result();
        try {
            result.setData(deptService.listDepts());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(page.getTotal());
        return result;
    }

    // 页面使用数据
    @GetMapping("/list")
    public String list(Model model) {
        //分页调用
        Page<Department> page = PageHelper.startPage(1, 6);
        Result result = new Result();
        try {
            //result.setData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("list", deptService.listDepts());
        return "dept";
    }

    @GetMapping("/add")
    public String add(String name) {

        Department department = new Department();
        department.setName(name);
        deptService.saveDepartment(department);

        return "hello world....";
    }
}
