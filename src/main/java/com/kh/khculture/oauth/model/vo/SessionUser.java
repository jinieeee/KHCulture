package com.kh.khculture.oauth.model.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionUser implements Serializable{
	private String name;
	private String email;
	
	public SessionUser(tempUser user){
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
