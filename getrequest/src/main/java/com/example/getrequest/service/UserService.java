package com.example.getrequest.service;

import com.example.getrequest.entity.User;
import com.example.getrequest.model.dto.UserDto;
import com.example.getrequest.model.request.CreateUserReq;
import com.example.getrequest.model.request.UpdateUserReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<UserDto> getListUser();
    public List<UserDto> getById( int id);
    public List<UserDto> searchByName(String keyword);
    public UserDto createUser(CreateUserReq req);
    public UserDto updateUser(UpdateUserReq req, int id);
    public boolean deleteUser(int id);
}
