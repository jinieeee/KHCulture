package com.kh.khculture.oauth.model.vo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// 추상클래스는 객체 생성이 불가하다. CustomOAuth2UserService에서 의존성 주입해도 오류 발생
// 왜 Repository 어노테이션을 지워야 하는지...?
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
}
