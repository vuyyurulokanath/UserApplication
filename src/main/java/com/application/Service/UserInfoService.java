package com.application.Service;






import com.application.Entity.UserDetailsInfo;

public interface UserInfoService {

	Iterable<UserDetailsInfo> findAll();

	UserDetailsInfo findById(Long id);

	void deleteById(Long id);

	UserDetailsInfo findByEmail(String email);

	UserDetailsInfo save(UserDetailsInfo userinfo);
}
