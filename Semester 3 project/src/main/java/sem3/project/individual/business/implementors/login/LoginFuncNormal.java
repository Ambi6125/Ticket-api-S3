package sem3.project.individual.business.implementors.login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sem3.project.individual.business.LoginFunctionality;
import sem3.project.individual.business.exceptions.InvalidCredentialsException;
import sem3.project.individual.domain.login.LoginRequest;
import sem3.project.individual.domain.login.LoginResponse;
import sem3.project.individual.misc.NotImplementedException;
import sem3.project.individual.persistence.AccountRepository;
import sem3.project.individual.persistence.entity.AccountEntity;

@Service
@RequiredArgsConstructor
public class LoginFuncNormal implements LoginFunctionality
{
    private AccountRepository accountRepo;
    private PasswordEncoder passwordEncoder;



    @Override
    public LoginResponse login(LoginRequest request) throws InvalidCredentialsException
    {
        AccountEntity foundUser = accountRepo.findByUsername(request.getUsername());
        if(foundUser == null)
        {
            throw new InvalidCredentialsException();
        }
        if(!passwordMatches(request.getPassword(), foundUser.getPassword()))
        {
            throw new InvalidCredentialsException();
        }

        throw new NotImplementedException();
    }

    private boolean passwordMatches(String password, String encodedPassword)
    {
        return passwordEncoder.matches(password, encodedPassword);
    }
}
