package com.lyl.mapper;

import com.lyl.Bean.Department;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    public Department getDepartBtId(Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")//将自增的id也放到department上
    @Insert("insert into department(departmentName) values (#{departmentName})")
    public  int insertDept(Department department);

    @Update("update department set departmentName=#{departmentName} where id=#{id}" )
    public int  update(Department department);
}
