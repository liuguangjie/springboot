package com.springboot.service;

import com.springboot.entity.Department;

import java.util.List;

public interface DeptService {
    List<Department> listDepts();

    void saveDepartment(Department department);
}
