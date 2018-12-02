package com.springboot.service.impl;

import com.springboot.entity.Department;
import com.springboot.mapper.DepartmentMapper;
import com.springboot.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    //@Transactional(readOnly=true)
    public List<Department> listDepts() {

        return departmentMapper.selectDeptList();
    }

    @Override
    @Transactional //事务配置成功
    public void saveDepartment(Department department) {
        departmentMapper.insertDepartment(department);
        //int a = 1/0;
    }


}
