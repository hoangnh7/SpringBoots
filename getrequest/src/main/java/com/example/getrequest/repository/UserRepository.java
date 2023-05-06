package com.example.getrequest.repository;

import com.example.getrequest.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByEmail(String email);
//    @Query(nativeQuery = true, name = "getUserInfo")
    User getUserInfo(int id);
//    List<User> findAllByNameLike(String name, Pageable pageable);

    // Kết quả trả về thông tin trang
//    Page<User> findAllByEmailLike(String email, Pageable pageable);
    public List<User> findAllByOrderByNameDesc();

}