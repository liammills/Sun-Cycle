package com.SunCycle.SunCycle.Repository;

import com.SunCycle.SunCycle.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Integer, User> {
}
