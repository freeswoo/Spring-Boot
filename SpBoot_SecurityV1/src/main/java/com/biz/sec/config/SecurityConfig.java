package com.biz.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import com.biz.sec.service.SecurityUserServiceImpl;

import lombok.RequiredArgsConstructor;

/*
 * spring security의 customizing 첫번째 단계
 * security-context.xml
 */
@RequiredArgsConstructor
@Configuration
// @EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final SecurityUserServiceImpl sUserService;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(web);
		
		/*
		 * security에서 검사하지 않을 요청
		 */
		web.ignoring()
			.antMatchers("/static/css/**","/static/js/**")
			.antMatchers("/static/images/**")
			.antMatchers("/static/music/**");
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(http);
		httpSecurity.authorizeRequests()
			.antMatchers("/hello").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user/login").permitAll()
			.antMatchers("/**").permitAll();
		
		httpSecurity.authorizeRequests()
			.and()
			.formLogin()
			.loginPage("/user/login")
			.failureUrl("/user/login?error")
			.usernameParameter("username")
			.passwordParameter("password")
			.and()
			.logout()
			.logoutSuccessUrl("/");
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(auth);
		auth.userDetailsService(sUserService)
			.passwordEncoder(
					passwordEncoder
			);
		
	}
	
	/*
	 * html templates 파일에서 sec: tag를 사용할때
	 * 값, 설정, 함수 등을 사용할수 있돌록 객체를 전달하는 용도
	 * 
	 * thymeleaf-extras-springsecurity5에서는
	 * Security 설정된 Config에서 자동 주입이 된다.
	 * 특히 Config 클래스를 WebSecurityConfigurerAdapter 상속받아서
	 * 작성할 경우 궅이 bean으로 설정하지 않아도 된다.
	 */
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}

}
