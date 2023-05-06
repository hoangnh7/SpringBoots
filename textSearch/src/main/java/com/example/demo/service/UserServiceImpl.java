package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.model.request.CreateUserReq;
import com.example.demo.model.request.UpdateUserReq;
import com.example.demo.repository.UserRepository;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserDto> getListUserByName(String keyword) throws InterruptedException {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(User.class)
                .get();

        org.apache.lucene.search.Query query = queryBuilder
                .keyword()
                .wildcard()
                .onFields("name")
                .matching("*" + keyword + "*") // Có thể regex
                .createQuery();

        org.hibernate.search.jpa.FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, User.class);
//        jpaQuery.setFirstResult(0); // tương đương offset 0
//        jpaQuery.setMaxResults(2); // tương đương limit 2

        List<User> users = jpaQuery.getResultList();

        // Convert User -> UserDto
        List<UserDto> rs = new ArrayList<UserDto>();
        for (User user : users) {
            rs.add(UserMapper.toUserDto(user));
        }

        return rs;
    }


//    @Autowired
//    IdentityCardRepository identityCardRepository;

    @Override
    public List<UserDto> getListUser() {
        List<User> users = userRepository.findAll();
        //convert to UserDTO
        List<UserDto> results = new ArrayList<UserDto>();
        for (User user : users) {
            results.add(UserMapper.toUserDto(user));
        }
        return results;
    }

    @Override
    public UserDto getById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
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
        //TO DO :KIỂM TRA EMAIL
        User rs = userRepository.findByEmail(req.getEmail());
        if (rs != null) {
            throw new NotFoundException("email is already exist");
        }
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

//    @Override
//    public UserDto updateUser(UpdateUserReq req, int id) {
//        // KIEEMR TRA USER DA TON TAI
//        User rs = userRepository.findById(id);
//        if (rs != null) {
//            throw new NotFoundException("User is existed");
//        }
//        Optional<User> result = userRepository.findById(id);
//        if (result.isEmpty()) {
//            throw new NotFoundException("No User found");
//
//        }
//        User user = result.get();
//        //UPDATE INFO
//        user.setName(req.getName());
//        user.setPhone(req.getPhone());
//        user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12)));
//        try {
//            userRepository.save(user);
//        } catch (Exception exception) {
////            throw new CustomExceptionHandler();
//        }
//        return UserMapper.toUserDto(user);
////        for (User user:users){
////            if (user.getId()== id){
////                //check email exist
////                for (User tmp : users) {
////                    if (tmp.getEmail().equals(req.getEmail())) {
////                        throw new DuplicateRecordException("New email already exists in the system");
////                    }
////                }
////                user.setEmail(req.getEmail());
////            }
////            user.setName(req.getName());
////            user.setPhone(req.getPhone());
////            user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12)));
////            return UserMapper.toUserDto(user);
////        }
////        throw new NotFoundException("No user found");
//    }

    @Override
    public void deleteUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("No user found");
        }
        try {
            userRepository.deleteById(id);
        } catch (Exception exception) {
            throw new InternalServerException("server error");
        }
    }
}
