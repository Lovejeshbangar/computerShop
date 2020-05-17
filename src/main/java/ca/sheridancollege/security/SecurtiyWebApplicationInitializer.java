package ca.sheridancollege.security;

import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurtiyWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurtiyWebApplicationInitializer() {
		super(SecurityConfig.class);
	}

	
}
