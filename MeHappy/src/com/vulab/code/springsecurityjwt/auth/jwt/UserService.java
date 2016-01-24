package com.vulab.code.springsecurityjwt.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vulab.code.dao.CustomerDao;
import com.vulab.code.domain.Customer;


@Service("userService")
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	@Autowired
	private CustomerDao customerDao;
	

	@Override
	@Transactional
	public final User loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User tempUser = null;
		Customer customer = customerDao.findByUsername(username);
		 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (customer != null) {
			
			 String hashedPassword = passwordEncoder.encode(customer.getPassword());
			tempUser = new User(username, hashedPassword, true, true, true, true,
					AuthorityUtils.createAuthorityList("user"));
		}
		
		return tempUser;
	}

	
}
