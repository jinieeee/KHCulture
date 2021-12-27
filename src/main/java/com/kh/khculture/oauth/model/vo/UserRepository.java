package com.kh.khculture.oauth.model.vo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// 왜 Repository 어노테이션을 지워야 하는지...?
public interface UserRepository extends JpaRepository<tempUser, Long>{
	Optional<tempUser> findByEmail(String email);
}
