package com.atqingke.ssmintegration.service.impl;

import com.atqingke.ssmintegration.mapper.EmployeeMapper;
import com.atqingke.ssmintegration.pojo.Employee;
import com.atqingke.ssmintegration.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 查询所有员工信息
     */
    @Override
    public List<Employee> findAll() {
        return employeeMapper.queryAll();
    }
}

