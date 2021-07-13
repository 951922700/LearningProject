package com.lyl.Controller;

import com.lyl.Bean.Department;
import com.lyl.Bean.Employee;
import com.lyl.mapper.DepartmentMapper;
import com.lyl.mapper.EmployeeMapper;
import com.lyl.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    EmployeeService employeeService;
    @GetMapping("/deptt/{id}")
    public Department get(@PathVariable("id") Integer id)
    {
        return departmentMapper.getDepartBtId(id);
    }
    @GetMapping("/dept")
    public Department insertDept(Department department)
    {
        departmentMapper.insertDept(department);
        return department;
    }


   @GetMapping("/employee/{id}")
    public Employee getEmp(@PathVariable("id") Integer id)
    {
        return employeeService.get(id);
    }
    @GetMapping("/empupdate")
    public Employee update(Employee employee){

        Employee emp = employeeService.updateEmp(employee);
        System.out.println(emp);
        return emp;
    }
}
