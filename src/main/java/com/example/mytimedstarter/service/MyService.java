package com.example.mytimedstarter.service;

import com.example.mytimedstarter.annotation.Timed;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Timed("Processing data")
    @SneakyThrows
    public void processData() {
        Thread.sleep(200);
        System.out.println("Data processed!");
    }

    @Timed
    @SneakyThrows
    public void fetchData() {
        Thread.sleep(200);
        System.out.println("Some data");
    }
}