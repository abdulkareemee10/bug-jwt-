package com.ktl.shipokauserservice.userLoginSession;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoginSessionRepository extends JpaRepository<UserLoginSession, String> {

    Optional<UserLoginSession> findByUserSessionId(String userSessionId);
}
