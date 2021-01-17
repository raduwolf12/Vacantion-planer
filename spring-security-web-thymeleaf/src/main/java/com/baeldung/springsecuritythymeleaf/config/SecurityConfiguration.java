package com.baeldung.springsecuritythymeleaf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.baeldung.springsecuritythymeleaf.auth.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//	@Autowired
//	private DataSource dataSource;
//	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/**")
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .successForwardUrl("/index")
            .and()
            .logout()
            .permitAll()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login");
    }
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().and().authorizeRequests().antMatchers("/**").hasRole("ADMIN").and()
//                .csrf().disable().headers().frameOptions().disable();
//    }
	
	
	
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//            .withUser("user")
//            .password(passwordEncoder().encode("password"))
//            .roles("USER")
//            .and()
//            .withUser("admin")
//            .password(passwordEncoder().encode("admin"))
//            .roles("ADMIN");
//    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) 
//      throws Exception {
//    	
//        auth.jdbcAuthentication()
//          .dataSource(dataSource)
//          .usersByUsernameQuery("SELECT *  FROM  angajati  WHERE angajati.angajat_name=? AND angajati.\"password\"= ?")
//          .authoritiesByUsernameQuery("admin");
//    }

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

}
