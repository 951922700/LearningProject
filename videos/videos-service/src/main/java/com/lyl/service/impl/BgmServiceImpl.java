package com.lyl.service.impl;

import com.lyl.mapper.BgmMapper;
import com.lyl.pojo.Bgm;
import com.lyl.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BgmServiceImpl implements com.lyl.service.BgmService  {

    @Autowired
    private BgmMapper bgmMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Bgm> queryBgmList() {
        return bgmMapper.selectAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Bgm queryBgmById(String bgmId) {
        /*Example bgmExample = new Example(Bgm.class);
        Example.Criteria criteria = bgmExample.createCriteria();
        criteria.andEqualTo("id",bgmId);
        return bgmMapper.selectOneByExample(bgmExample);*/
        return bgmMapper.selectByPrimaryKey(bgmId);//根据主键查询
    }
}
