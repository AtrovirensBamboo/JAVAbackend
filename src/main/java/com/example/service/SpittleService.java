package com.example.service;

import com.example.model.Spitter;
import com.example.model.Spittle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpittleService{

    List<Spittle> query(long max,int count);

    void insertSpitter(Spitter spitter);

    Spitter querySpitter(String username);
}
