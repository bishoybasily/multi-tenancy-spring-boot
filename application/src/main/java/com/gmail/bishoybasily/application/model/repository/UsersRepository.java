package com.gmail.bishoybasily.application.model.repository;

import com.gmail.bishoybasily.application.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bishoybasily
 * @since 2020-12-07
 */
public interface UsersRepository extends JpaRepository<User, String> {
}
