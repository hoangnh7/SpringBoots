package com.example.demo.model.mapper;

import com.example.demo.entity.User;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.request.CreateUserReq;
import org.mindrot.jbcrypt.BCrypt;

public class UserMapper {
    public static UserDto toUserDto(User user){
        UserDto tmp = new UserDto();
        tmp.setId(user.getId());
        tmp.setName(user.getName());
        tmp.setAvatar(user.getAvatar());
        tmp.setPhone(user.getPhone());
        tmp.setEmail(user.getEmail());
        tmp.setBirthday(user.getBirthday());
        return tmp;
    }
    public static User toUser(CreateUserReq req){
        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        // Hash password using BCrypt
        String hash = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);
        user.setRole("USER");
        return user;
    }
}
