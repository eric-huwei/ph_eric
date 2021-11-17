package com.eric.repository;

import com.eric.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2020/1/4 下午2:59
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
