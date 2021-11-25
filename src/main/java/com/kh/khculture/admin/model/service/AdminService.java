package com.kh.khculture.admin.model.service;

import java.util.Map;

import com.kh.khculture.admin.model.vo.Search;

public interface AdminService {

	Map<String, Object> getAllMemberList(Search search);

}
