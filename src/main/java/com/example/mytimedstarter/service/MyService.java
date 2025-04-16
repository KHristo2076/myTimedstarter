package com.example.mytimedstarter.service;

import com.example.mytimedstarter.annotation.Timed;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Timed("Processing data")
    public void processData() throws InterruptedException {
        Thread.sleep(200);
        System.out.println("Data processed!");
    }

    @Timed
    public void fetchData()throws InterruptedException {
        Thread.sleep(200);
        System.out.println("Some data");
    }
}