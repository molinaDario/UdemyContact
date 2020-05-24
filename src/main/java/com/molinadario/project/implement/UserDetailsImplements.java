package com.molinadario.project.implement;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.molinadario.project.entity.Role;
import com.molinadario.project.entity.Usuarios;
import com.molinadario.project.repository.UsuarioRepository;

@Service("userDetailsImplements")
@Transactional
public class UserDetailsImplements implements UserDetailsService {

	private static final Log LOG = LogFactory.getLog(UserDetailsImplements.class);

	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		LOG.info("METHOD loadUserByUsername() PARAM: username" + username.toString());

		Usuarios user = usuarioRepository.findByUsername(username);

		Set<GrantedAuthority> grantedAuthorityList = new HashSet<>();

		for (Role roles : user.getRoles()) {

			GrantedAuthority GrantedAuthority = new SimpleGrantedAuthority(roles.getDescription());

			grantedAuthorityList.add(GrantedAuthority);

		}

		UserDetails userDetails = (UserDetails) new User(user.getUsername(), user.getPassword(), grantedAuthorityList);

		return userDetails;
	}

}
