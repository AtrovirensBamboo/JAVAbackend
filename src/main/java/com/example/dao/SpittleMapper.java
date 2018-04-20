package com.example.dao;

import com.example.model.Spitter;
import com.example.model.Spittle;

import java.util.List;

public interface SpittleMapper {

    List<Spittle> query(long max,int count);

    void insertSpitter(Spitter spitter);

    Spitter querySpitter(String username);

}
