package com.lyl.Controller;

import com.lyl.dao.DepartmentDao;
import com.lyl.dao.EmployeeDao;
import com.lyl.entities.Department;
import com.lyl.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    //查询所有员工返回列表页面
    @Autowired
    DepartmentDao departmentDao;
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps",all);
        //thymeleaf会进行拼串   classpath:/templates/
        return "emp/list";
    }

    //去员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> depts = departmentDao.getDepartments();
        System.out.println(depts);
        model.addAttribute("depts",depts);
        return "emp/add";
    }

    //员工添加
    @PostMapping(value = "/emp")
    public String addEmp(Employee employee){
        //来到员工列表页面  不能直接return"/emps" 因为这样会去找模板引擎
        //应该用redirect或者forward /代表当前项目下的路径
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 来到修改岩棉，查出当前员工，并且回显
     * @param id
     * @return
     */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee=employeeDao.get(id);
        model.addAttribute("emp",employee);
        Collection<Department> depts = departmentDao.getDepartments();
        model.addAttribute("depts",depts);
        return "emp/add";
    }

    /**
     * 编辑员工信息
     */
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:emps";
    }

    /**
     * 员工删除
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){

        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
