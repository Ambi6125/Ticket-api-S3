package sem3.project.individual.business.implementors.accounts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import sem3.project.individual.business.exceptions.UsernameTakenException;
import sem3.project.individual.domain.accounts.CreateAccountRequest;
import sem3.project.individual.domain.accounts.CreateAccountResponse;
import sem3.project.individual.persistence.AccountRepository;
import sem3.project.individual.persistence.entity.AccountEntity;

@ExtendWith(MockitoExtension.class)
class CreateAccountFunctionalityNormalTest {

    @Mock
    private AccountRepository mockRepo;
    @Mock
    private PasswordEncoder mockEncoder;
    @InjectMocks
    private CreateAccountFunctionalityNormal useCase;

    @Test
    void createAccount_UsernameNotTaken_ReturnsResponseWithAccountId() {
        // Arrange
        CreateAccountRequest request = new CreateAccountRequest("username", "password", "email");
        AccountEntity savedAccount = AccountEntity.builder()
                .id(1L)
                .username("username")
                .password("encodedPassword")
                .email("email")
                .build();

        when(mockRepo.existsByUsername(request.getUsername())).thenReturn(false);
        when(mockEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(mockRepo.save(any(AccountEntity.class))).thenReturn(savedAccount);

        // Act
        CreateAccountResponse response = useCase.createAccount(request);

        // Assert
        assertEquals(1L, response.getId());
        verify(mockRepo).existsByUsername(request.getUsername());
        verify(mockEncoder).encode(request.getPassword());
        verify(mockRepo).save(any(AccountEntity.class));
    }

    @Test
    void createAccount_UsernameTaken_ThrowsUsernameTakenException() {
        // Arrange
        CreateAccountRequest request = new CreateAccountRequest("existingUsername", "password", "email");

        when(mockRepo.existsByUsername(request.getUsername())).thenReturn(true);



        // Act & Assert
        verifyNoMoreInteractions(mockRepo);
        assertThrows(UsernameTakenException.class, () -> useCase.createAccount(request));
        verify(mockRepo).existsByUsername(request.getUsername());
        verifyNoInteractions(mockEncoder);

    }

}
