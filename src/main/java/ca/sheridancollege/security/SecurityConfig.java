package ca.sheridancollege.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	LoginAccessDeniedHandler accessDeniedHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// List protections from most secure to lease secure
		http
		.authorizeRequests()
		.antMatchers("/index").hasAnyRole("ADMIN","EMPLOYEE")
		.antMatchers(HttpMethod.POST, "/register").permitAll()
		.antMatchers("/","/style/**","/images/**","/javascript/**", "/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login").permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/loginPage?logout").permitAll()
		.and()
		.exceptionHandling()
		.accessDeniedHandler(accessDeniedHandler);
	}


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        	.passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("admin").password("root").roles("ADMIN")
            .and()
                .withUser("employee").password("root").roles("EMPLOYEE");
    }

}
