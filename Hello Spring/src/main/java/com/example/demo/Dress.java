package com.example.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Primary
public class Dress implements Outfit{
    public void wear() {
        System.out.println("Đang mặc váy");
    }
}
