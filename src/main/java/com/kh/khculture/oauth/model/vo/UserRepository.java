package com.kh.khculture.oauth.model.vo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

// interface는 빈 등록 불가능
// CustomOAuth2UserService required a bean of type 'com.kh.khculture.oauth.model.vo.UserRepository' that could not be found.
public interface UserRepository extends JpaRepository<tempUser, Long>{
	Optional<tempUser> findByEmail(String email);
}
