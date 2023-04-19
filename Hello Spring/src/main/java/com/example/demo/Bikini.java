package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Bikini implements Outfit {
 public void wear(){
     System.out.println("đang mặc bikini");
 }
}
