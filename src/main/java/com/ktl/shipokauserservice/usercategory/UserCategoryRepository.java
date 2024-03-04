package com.ktl.shipokauserservice.usercategory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {

Optional<UserCategory> findByCategoryName(String name);
}
