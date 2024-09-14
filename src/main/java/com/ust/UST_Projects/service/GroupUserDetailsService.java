package com.ust.UST_Projects.service;

import com.ust.UST_Projects.model.Person;
import com.ust.UST_Projects.repository.Personrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupUserDetailsService implements UserDetailsService {


    @Autowired
    private Personrepo personrepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> user=personrepo.findByUsername(username);
    return user.map(GroupUserDetails::new)
            .orElseThrow(()->new UsernameNotFoundException(username+"Not Found"));
    }
}
