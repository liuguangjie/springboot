package com.springboot.mapper;

import com.springboot.entity.Department;

import java.util.List;

public interface DepartmentMapper {

    List<Department> selectDeptList();
}
