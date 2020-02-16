package com.vedantu.security;

import com.vedantu.daos.UserDao;
import com.vedantu.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/*
From this class using loadByUsername method, implicitly DaoAuthenticationProvider will take the UserDetails object and
compare with the authentication object given at AuthenticationManager
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userDao.getUserDetailsByUsername(username);
        if(userModel == null){
            throw new UsernameNotFoundException(username);
        }
/*        System.out.println("hello service");*/
        ArrayList<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        SimpleGrantedAuthority authority;
        for(String role: userModel.getAuthorities()){
            authority = new SimpleGrantedAuthority(role);
            list.add(authority);
        }

        return new User(userModel.getUsername(), userModel.getPassword(), list);
    }
}
