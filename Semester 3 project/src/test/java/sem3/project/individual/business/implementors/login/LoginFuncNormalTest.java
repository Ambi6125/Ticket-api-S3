package sem3.project.individual.business.implementors.login;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import sem3.project.individual.business.AccessTokenEncoder;
import sem3.project.individual.business.exceptions.InvalidCredentialsException;
import sem3.project.individual.domain.accounts.AccountRole;
import sem3.project.individual.domain.login.LoginRequest;
import sem3.project.individual.domain.login.LoginResponse;
import sem3.project.individual.domain.login.tokens.AccessToken;
import sem3.project.individual.persistence.AccountRepository;
import sem3.project.individual.persistence.entity.AccountEntity;
import sem3.project.individual.persistence.entity.AccountRoleEntity;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginFuncNormalTest {

    @Mock
    private AccountRepository accountRepo;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AccessTokenEncoder tokenEncoder;

    @InjectMocks
    private LoginFuncNormal useCase;

    @Test
    void login() {
        LoginRequest request = new LoginRequest("Ambi", "12345678");

        AccountEntity result = AccountEntity.builder()
                .id(1L)
                .username("Ambi")
                .password("12345678")
                .email("massiveCock@gmail.com")
                .build();

        result.setRoles(Set.of(AccountRoleEntity.builder().account(result).role(AccountRole.ADMIN).build()));

        List<String> roles = result.getRoles().stream().map(role -> role.getRole().toString()).toList();

        AccessToken token = AccessToken.builder()
                .accountId(result.getId())
                .roles(roles)
                .subject(result.getUsername())
                .build();

        String returnedToken = "LargeChickenOnDonkey";

        LoginResponse expectedResponse = LoginResponse.builder()
                .accessToken(returnedToken)
                .build();

        when(accountRepo.findByUsername(request.getUsername())).thenReturn(result);
        when(passwordEncoder.matches(request.getPassword(), result.getPassword())).thenReturn(true);
        when(tokenEncoder.encode(token)).thenReturn(returnedToken);

        LoginResponse actual = useCase.login(request);
        assertEquals(expectedResponse, actual);

        verify(accountRepo).findByUsername(request.getUsername());
        verify(passwordEncoder).matches(request.getPassword(), result.getPassword());
        verify(tokenEncoder).encode(token);
    }
    
    @Test
    void login_UsernameDoesNotExist_ThrowsInvalidCredentialsException()
    {
        LoginRequest request = new LoginRequest("nameThatDoesNotExist", "123");

        when(accountRepo.findByUsername(request.getUsername())).thenReturn(null);

        assertThrows(InvalidCredentialsException.class, () -> useCase.login(request));

        verify(tokenEncoder,  never()).encode(any());
    }

    @Test
    void login_UsernameExistsButIncorrectPassword_ThrowsInvalidCredentialsException()
    {
        LoginRequest request = new LoginRequest("Correct Username", "Incorrect password");
        AccountEntity accountThatMatchesUsername = AccountEntity.builder()
                .username("Correct Username")
                .password("Real password")
                .build();

        when(accountRepo.findByUsername(request.getUsername())).thenReturn(accountThatMatchesUsername);

        when(passwordEncoder.matches(request.getPassword(),accountThatMatchesUsername.getPassword()))
                .thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> useCase.login(request));

        verify(tokenEncoder, never()).encode(any());
    }
}