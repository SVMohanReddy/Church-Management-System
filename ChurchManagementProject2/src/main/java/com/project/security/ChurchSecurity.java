package com.project.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ChurchSecurity {
	
	@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
//	@Bean
//	public UserDetailsManager userDetailsManager(DataSource dataSource)
//	{
//		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//		
//		jdbcUserDetailsManager.setUsersByUsernameQuery(
//				"select user_name, password, active from member where user_name=?");
//
//		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
//				"select user_name, role from role where user_name=?");
//		
//		return jdbcUserDetailsManager;
//	}
//
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//		http.authorizeHttpRequests(configurer -> configurer
//				
//				//SuperAdmin Access
//				.requestMatchers(HttpMethod.POST, "/superadmin/create/admin").hasRole("SUPERADMIN")
//				.requestMatchers(HttpMethod.GET, "/superadmin/getList/admin").hasRole("SUPERADMIN")
//				.requestMatchers(HttpMethod.DELETE, "/supeeradmin/deleteAdmin/{id},{userType}").hasRole("SUPERADMIN")
//				.requestMatchers(HttpMethod.POST, "/superadmin/create/church").hasRole("SUPERADMIN")
//				.requestMatchers(HttpMethod.GET, "/superadmin/getAllChurches").hasRole("SUPERADMIN")
//				.requestMatchers(HttpMethod.PUT, "/superadmin/updateChurch/{id}").hasRole("SUPERADMIN")
//				.requestMatchers(HttpMethod.DELETE, "/superadmin/deleteChurchById/{id}").hasRole("SUPERADMIN")
//				
//				//ADMIN Access
//				.requestMatchers(HttpMethod.POST, "/admin/createUser").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.DELETE, "/admin//deleteUser/{Id},{userType}").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.POST, "/admin/churchEvent/create").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.GET, "/admin/eventsByType/{eventType}").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.GET, "/admin/eventsList").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.PUT, "/admin/churchEvent/{eventId}").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.DELETE, "/admin/events/{eventId}").hasRole("ADMIN")
//				
//				//UserAccess
//				.requestMatchers(HttpMethod.PUT, "/user/updateById/{id}").hasRole("USER")
//				.requestMatchers(HttpMethod.POST, "/user/userEvents/createByName/{userName}").hasRole("USER")
//				.requestMatchers(HttpMethod.GET, "/user/getUserEvents/{userName}").hasRole("USER")
//				.requestMatchers(HttpMethod.PUT, "/user/upadateEventById/{eventId}").hasRole("USER")
//				.requestMatchers(HttpMethod.DELETE, "/user/deleteEventById/{eventId}").hasRole("USER"));
//
//		http.httpBasic(Customizer.withDefaults());
//		http.csrf().disable();
//
//		return http.build();
//	}
}
