package com.example.getrequest.service;

import com.example.getrequest.entity.User;
import com.example.getrequest.exception.CustomExceptionHandler;
import com.example.getrequest.exception.DuplicateRecordException;
import com.example.getrequest.exception.NotFoundException;
import com.example.getrequest.model.dto.UserDto;
import com.example.getrequest.model.mapper.UserMapper;
import com.example.getrequest.model.request.CreateUserReq;
import com.example.getrequest.model.request.UpdateUserReq;
import com.example.getrequest.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService{
//    private static ArrayList <User> users = new ArrayList<User>();
//    static {
//        users.add(new User(1, "Nguyễn Thị Mộng Mơ", "mongmo@gmail.com","0987654321","avatar.img","123"));
//        users.add(new User(2, "Bùi Như Lạc", "laclac@gmail.com","0123456789","avatar1.img","123"));
//        users.add(new User(3, "Phan Thị Lỏng Lẻo", "longleo@gmail.com","0987564664","avatar3.img","123"));
//        users.add(new User(4, "Bành Thị Tèo", "teo@gmail.com","0874845455","avatar9.img","123"));
//    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getListUser() {
        List<User> users= userRepository.findAll();

        //convert to UserDTO
        List<UserDto> results = new ArrayList<UserDto>();
        for (User user : users){
            results.add(UserMapper.toUserDto(user));
        }
        return results;
    }

    @Override
    public UserDto getById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new NotFoundException("No user found");
        }
//        List<UserDto> results= new ArrayList<UserDto>();
//        for (User user:users){
//            if (user.getId()==id){
//               results.add(UserMapper.toUserDto(user));
//               return results;
//            }
//        }
//        throw new NotFoundException("No user found");
        return UserMapper.toUserDto(user.get());
    }

    @Override
    public List<UserDto> searchByName(String keyword) {
//        List<UserDto> result = new ArrayList<UserDto>();
//        for (User user: users){
//            if (user.getName().contains(keyword)){
//                result.add(UserMapper.toUserDto(user));
//            }
//        }
//        return result;
          return null;
    }

    @Override
    public UserDto createUser(CreateUserReq req) {
        // Convert CreateUserReq -> User
        User user = new User();
//        user.setId(users.size()+1);
        user.setPhone(req.getPhone());
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        // Mã hóa mật khẩu sử dụng BCrypt
        user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12)));

        // Thêm user
//        users.add(user);
        userRepository.save(user);
        return UserMapper.toUserDto(user);
//        return null;
    }

    @Override
    public UserDto updateUser(UpdateUserReq req, int id) {
        // KIEEMR TRA USER DA TON TAI
        User rs = userRepository.getUserInfo(id);
        if (rs!=null){
            throw new NotFoundException("User is existed");
        }
        Optional<User> result = userRepository.findById(id);
        if (result.isEmpty()){
            throw new NotFoundException("No User found");

        }
        User user = result.get();
        //UPDATE INFO
        user.setName(req.getName());
        user.setPhone(req.getPhone());
        user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12)));
        try {
            userRepository.save(user);
        }catch (Exception exception){
//            throw new CustomExceptionHandler();
        }
        return UserMapper.toUserDto(user);
//        for (User user:users){
//            if (user.getId()== id){
//                //check email exist
//                for (User tmp : users) {
//                    if (tmp.getEmail().equals(req.getEmail())) {
//                        throw new DuplicateRecordException("New email already exists in the system");
//                    }
//                }
//                user.setEmail(req.getEmail());
//            }
//            user.setName(req.getName());
//            user.setPhone(req.getPhone());
//            user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12)));
//            return UserMapper.toUserDto(user);
//        }
//        throw new NotFoundException("No user found");
    }

    @Override
    public void deleteUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new NotFoundException("No user found");
        }
        try {
            userRepository.deleteById(id);
        }catch (Exception exception){

        }
//        for (User user: users){
//            if (user.getId() == id){
//                users.remove(user);
//                return true;
//            }
//        }
//        throw new NotFoundException("No User found");
//        return null;
    }
//    public List<User> getListUser() {
//        return users;
//    }
}
