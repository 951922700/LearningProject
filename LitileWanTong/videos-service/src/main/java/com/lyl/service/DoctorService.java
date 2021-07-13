package com.lyl.service;

import com.lyl.pojo.Doctor;

import java.util.List;

public interface DoctorService {
    /**
     * 查询医生信息
     */
    public List<Doctor> getAll();
}
