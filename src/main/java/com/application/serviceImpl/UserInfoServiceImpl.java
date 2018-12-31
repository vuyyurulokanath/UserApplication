package com.application.serviceImpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.Entity.UserDetailsInfo;
import com.application.Repository.UserDetailsRepository;
import com.application.Service.UserInfoService;
@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	

	@Override
	public Iterable<UserDetailsInfo> findAll() {
		
		return userDetailsRepository.findAll();
	}

	@Override
	public UserDetailsInfo findById(Long id) {
				return userDetailsRepository.getOne(id);
	}

	@Override
	public void deleteById(Long id) {
		userDetailsRepository.deleteById(id);
	}

	@Override
	public UserDetailsInfo findByEmail(String email) {
		return userDetailsRepository.findByEmail(email);
	}

	@Override
	public UserDetailsInfo save(UserDetailsInfo userInfo) {
		return userDetailsRepository.save(userInfo);
	}

}
