package sem3.project.individual.business.implementors.login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sem3.project.individual.business.AccessTokenEncoder;
import sem3.project.individual.business.LoginFunctionality;
import sem3.project.individual.business.exceptions.InvalidCredentialsException;
import sem3.project.individual.domain.login.LoginRequest;
import sem3.project.individual.domain.login.LoginResponse;
import sem3.project.individual.domain.login.tokens.AccessToken;
import sem3.project.individual.persistence.AccountRepository;
import sem3.project.individual.persistence.entity.AccountEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginFuncNormal implements LoginFunctionality
{
    private final AccountRepository accountRepo;
    private final PasswordEncoder passwordEncoder;

    private final AccessTokenEncoder tokenEncoder;



    @Override
    public LoginResponse login(LoginRequest request)
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

        String token = generateToken(foundUser);
        return LoginResponse.builder().accessToken(token).build();
    }

    private boolean passwordMatches(String password, String encodedPassword)
    {
        return passwordEncoder.matches(password, encodedPassword);
    }

    private String generateToken(AccountEntity account)
    {
        List<String> roles = account.getRoles().stream()
                .map(role -> role.getRole().toString())
                .toList();

        AccessToken token = AccessToken.builder()
                .subject(account.getUsername())
                .roles(roles)
                .accountId(account.getId())
                .build();

        return tokenEncoder.encode(token);
    }
}
