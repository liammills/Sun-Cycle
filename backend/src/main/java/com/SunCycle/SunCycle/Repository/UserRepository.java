package com.SunCycle.SunCycle.Repository;

import com.SunCycle.SunCycle.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
