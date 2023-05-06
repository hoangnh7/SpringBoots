package com.example.demo.service;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.request.CreateUserReq;
import com.example.demo.model.request.UpdateUserReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<UserDto> getListUserByName(String keyword) throws InterruptedException;
    public List<UserDto> getListUser();
    public UserDto getById( int id);
    public List<UserDto> searchByName(String keyword);
    public UserDto createUser(CreateUserReq req);
//    public UserDto updateUser(UpdateUserReq req, int id);
    public void deleteUser(int id);
}
