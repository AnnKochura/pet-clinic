package com.home.project.pet.clinic.services;

import com.home.project.pet.clinic.entity.security.Role;
import com.home.project.pet.clinic.entity.security.User;
import com.home.project.pet.clinic.repository.UserRepository;
import com.home.project.pet.clinic.utils.Util;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService extends SimpleUrlLogoutSuccessHandler implements UserDetailsService, LogoutSuccessHandler {

    private static final Logger LOG = Logger.getLogger(UserService.class);

    final UserRepository uRepo;

    public UserService(UserRepository uRepo) {
        this.uRepo = uRepo;
    }

    // security login
    @Override
    public UserDetails loadUserByUsername(String email) {
        UserDetails userDetails = null;
        Optional<User> oUser = uRepo.findByEmailEqualsAllIgnoreCase(email);
        if (oUser.isPresent()) {
            User us = oUser.get();

                userDetails = new org.springframework.security.core.userdetails.User(
                    us.getEmail(),
                    us.getPassword(),
                    us.isEnabled(),
                    true,
                    true,
                    true,
                    getAuthorities(us.getRoles()));
        } else {
            LOG.info("Username or password incorrect");
            throw new UsernameNotFoundException("Username or password incorrect");
        }
        return userDetails;
    }

    private List<GrantedAuthority> getAuthorities(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRo_name()));
        }
        return authorities;
    }

    public User register(User us) throws AuthenticationException {

        if (!Util.isEmail(us.getEmail())) {
            Util.log("This mail format is incorrect!", this.getClass());
            throw new AuthenticationException("This mail format is incorrect!");
        }

        Optional<User> uOpt = uRepo.findByEmailEqualsAllIgnoreCase(us.getEmail());
        if (uOpt.isPresent()) {
            Util.log("This user is already registered!", this.getClass());
            throw new AuthenticationException("This user is already registered!");
        }
        us.setPassword(encoder().encode(us.getPassword()));
        return uRepo.save(us);
    }

    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("onLogoutSuccess Call ");
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
    }


}
