package com.example.mytimedstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyTimedstarterApplication {
    private static MyService myService;
    public MyTimedstarterApplication(MyService myService) {
        MyTimedstarterApplication.myService = myService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MyTimedstarterApplication.class, args);

        myService.processData();
        myService.fetchData();
    }

}
