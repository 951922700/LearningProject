package com.lyl.mapper;

import com.lyl.Bean.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//@Mapper
@Repository
public interface EmployeeMapper {
    public Employee gettt(Integer id);

    public void insertEmp(Employee employee);

    public void update(Employee employee);

    public void delete(Integer id);
}
