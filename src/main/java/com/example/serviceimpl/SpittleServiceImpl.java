package com.example.serviceimpl;

import com.example.common.Common;
import com.example.dao.SpittleMapper;
import com.example.model.Spitter;
import com.example.model.Spittle;
import com.example.service.SpittleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpittleServiceImpl implements SpittleService {
    private SpittleMapper spittleMapper;

    public SpittleMapper getSpittleMapper() {
        return  spittleMapper;
    }
    @Autowired
    public void setSpittleMapper(SpittleMapper spittleMapper) {
        this.spittleMapper = spittleMapper;
    }
    @Override
    public List<Spittle> query(long max,int count){
        return spittleMapper.query(max,count);
    }
    @Override
    public void insertSpitter(Spitter spitter){
        spittleMapper.insertSpitter(spitter);
    }
    @Override
    public Spitter querySpitter(String username){
        return spittleMapper.querySpitter(username);
    }
}

