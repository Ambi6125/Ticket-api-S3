package sem3.project.individual.business;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.project.individual.business.exceptions.InvalidCredentialsException;
import sem3.project.individual.domain.login.LoginRequest;
import sem3.project.individual.domain.login.LoginResponse;
import sem3.project.individual.persistence.AccountRepository;

@Service
public interface LoginFunctionality
{
    LoginResponse login(LoginRequest request) throws InvalidCredentialsException;
}
