package study.security.authoritytest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(
                        User.withDefaultPasswordEncoder()
                                .username("user1")
                                .password("1234")
                                .roles("USER", "STUDENT")
                ).withUser(
                        User.withDefaultPasswordEncoder()
                                .username("user2")
                                .password("1234")
                                .roles("USER", "STUDENT")
                ).withUser(
                        User.withDefaultPasswordEncoder()
                                .username("tutor1")
                                .password("1234")
                                .roles("USER", "TUTOR")
                ).withUser(
                        User.withDefaultPasswordEncoder()
                                .username("admin")
                                .password("1234")
                                .roles("ADMIN")
                );
    }

    public AccessDecisionManager accessDecisionManager() {
        return new AccessDecisionManager() {
            @Override
            public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
                throw new AccessDeniedException("?????? ??????");
            }

            @Override
            public boolean supports(ConfigAttribute attribute) {
                return true;
            }

            @Override
            public boolean supports(Class<?> clazz) {
                return FilterInvocation.class.isAssignableFrom(clazz);
            }
        };
    }

    @Autowired private NameCheck nameCheck;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic()
                .and()
                    .authorizeRequests()
                    .mvcMatchers("/greeting/{name}")
                        .access("@nameCheck.check(#name)")
                    .anyRequest().authenticated()
//                    .accessDecisionManager(accessDecisionManager())
        ;
    }
}
