package com.molinadario.project.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.molinadario.project.entity.Authority;
import com.molinadario.project.entity.Users;
import com.molinadario.project.repository.UsersRepository;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier("usersRepository")
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users users = usersRepository.findByUsername(username);

		List<GrantedAuthority> grantList = new ArrayList();

		for (Authority authority : users.getAuthority()) {

			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());

			grantList.add(grantedAuthority);
		}

		UserDetails usersDetails = (UserDetails) new User(users.getUsername(), users.getPassword(), grantList);

		return usersDetails;
	}

}
