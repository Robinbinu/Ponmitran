package com.diagnoPlant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diagnoPlant.models.Role;
import com.diagnoPlant.models.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        User user = userService.findByUserName(userName);
        List<GrantedAuthority> authorities = getUserAutority(user.getRoles());
        return buildUserForAutentication(user, authorities);
    }

    private List<GrantedAuthority> getUserAutority(Set<Role> userRoles){
        Set<GrantedAuthority> roles = new HashSet<>();
        for(Role role : userRoles){
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAutentication (User user, List<GrantedAuthority> authorities){
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                user.getActive(),true, true, true, authorities);
    }

}