package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Profile {
 public String FullName;
 public String Email;
 public Profile(String FullName, String Email){
     this.FullName= FullName;
     this.Email = Email;
 }
}
