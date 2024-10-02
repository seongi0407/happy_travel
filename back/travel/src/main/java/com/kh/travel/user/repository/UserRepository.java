package com.kh.travel.user.repository;

import com.kh.travel.common.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    // 아이디 중복검사
    UserEntity findUserById(String userId);
} // interface
