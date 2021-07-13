package com.lyl.service.impl;

import com.lyl.mapper.DoctorMapper;
import com.lyl.pojo.Doctor;
import com.lyl.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;
    @Override
    public List<Doctor> getAll() {
        return doctorMapper.selectAll();
    }
}
