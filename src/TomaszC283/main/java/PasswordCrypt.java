package TomaszC283.main.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordCrypt {

	static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	  @Bean
		public static String passwordEncoder(String password){

			String hashedPassword = encoder.encode(password);
			
			return hashedPassword;
		}
	
	  @Bean
	  public static Boolean passwordMatcher( String passwordTyped, String passwordFromDB ) {
		  return encoder.matches(passwordTyped, passwordFromDB );
	  }
}

