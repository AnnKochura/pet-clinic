package com.home.project.pet.clinic.web.security;

import com.home.project.pet.clinic.repository.UserFollowInRepository;
import com.home.project.pet.clinic.services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    final UserService userService;

    public WebSecurityConfig(UserService userService, UserFollowInRepository userFollowInRepository) {
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(userService.encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin().and()
                .authorizeRequests()
                //----------------HTML-PAGES----------------------------------------------------------------------------
                .antMatchers("/calender/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/category/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/changepassword/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY", "BEGINNER")
                .antMatchers("/customer/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/customergroup/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/customerinfo/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/customerlist/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/dashboard/**").hasAnyRole("SECRETARY", "ADMIN", "DOCTOR", "BEGINNER")
                .antMatchers("/diary/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/lab/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/payment/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/product/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/purchase/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                //.antMatchers("/register/**").permitAll()//Veri tabanı sıfır olduğunda yeni admin oluşturulabilmesi için.
                .antMatchers("/register/**").hasRole("ADMIN")
                .antMatchers("/sale/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/statistic/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/suppliers/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/takvim/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                //----------------MASTER-PAGE---------------------------------------------------------------------------
                .antMatchers("/header/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY", "BEGINNER")
                .antMatchers("/layout/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY", "BEGINNER")
                .antMatchers("/sidebar/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY", "BEGINNER")
                //----------------REST-CONTROLLER-----------------------------------------------------------------------------
                .antMatchers("/profile/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY", "BEGINNER")
                .antMatchers("/restcalender/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                //------------------FRONT-END---------------------------------------------------------------------------
                .antMatchers("/css/**").permitAll()
                .antMatchers("/dist/**").permitAll()
                .antMatchers("/gifs/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/uploads/**").permitAll()
                //----------------404-ERROR-----------------------------------------------------------------------------
                .antMatchers("/403/**").permitAll()
                .antMatchers("/404/**").permitAll()
                .antMatchers("/405/**").permitAll()
                //------------------------------------------------------------------------------------------------------
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessHandler(userService)
                .permitAll();
        http.csrf().disable();
    }

    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
}
