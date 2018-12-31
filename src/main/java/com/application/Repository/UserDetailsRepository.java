package com.application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;





import com.application.Entity.UserDetailsInfo;

public interface UserDetailsRepository extends JpaRepository<UserDetailsInfo, Long> {

	UserDetailsInfo findByEmail(String email);

}
