package com.lyl.service;


import com.lyl.Bean.Employee;
import com.lyl.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

//可以再类上指定cache的全局规则
@CacheConfig(cacheNames = "emp")
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;


    /**
     * 开启缓存
     *
     *  CacheManager管理多个Cache组件 对缓存的真正CRUD操作在Cache组件中 每一个缓存组件有自己唯一一个名字
     *  属性：
     *  cacheName/value:指定缓存组件的名字
     *  key:缓存数据使用的key；默认使用方法参数的值
     *  keyGenerator 二选一
     *  CacheManager指定缓存管理器
     *  cacheRosovler二选一
     *  condition指定条件
     *  unless否定   本来缓存  除非满足条件就不满足了  可以用结果作为条件 unless="#result==null"
     *  sync 异步
     * @param id
     * @return
     */
    @Cacheable(cacheNames = {"emp"},key="#id",condition = "#id>0")
    public Employee get(Integer id)
    {
        System.out.println("查询"+id+"号员工");
        Employee emp=employeeMapper.gettt(id);
        return emp;
    }

    /**
     * @CachePut 既调用方法，又更新缓存数据
     * 修改了数据 同时更新缓存
     *
     * 因为上面的key是1  下面不写key默认是employee对象 所以更新后查询对象仍然是更新前的缓存
     * 也可以result.id  but Cacheable不能用
     */
    @CachePut(value = "emp",key = "#employee.id")
    public Employee updateEmp(Employee employee){
        employeeMapper.update(employee);
        return employee;
    }

    /**
     * @CacheEvict  缓存清除
     * allEntries=true  删除emp里的所有key value
     * beforeInvocation=false 缓存的清除是否在方法执行之前执行
     * 默认是在方法执行之后进行的
     */
    @CacheEvict(value = "emp",key = "#id")
    public void delete(Integer id){
        System.out.println("delete员工");
        employeeMapper.delete(id);
    }

    /**
     * @Caching 定义复杂规则
     *
     */
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp",key = "#result.id"),
                    @CachePut(value = "emo",key = "#result.email")
            }
    )
    public Employee get(String lastName){
        return new Employee();
    }
}
