package sem3.project.individual.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig
{
    public PasswordEncoder createBCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
