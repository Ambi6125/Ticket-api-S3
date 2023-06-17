package sem3.project.individual.business.implementors.accounts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.project.individual.business.exceptions.InvalidTokenException;
import sem3.project.individual.domain.accounts.AccountRankingResponse;
import sem3.project.individual.domain.accounts.AccountRole;
import sem3.project.individual.domain.accounts.GetAccountResponse;
import sem3.project.individual.domain.accounts.GetMultipleAccountsResponse;
import sem3.project.individual.domain.login.tokens.AccessToken;
import sem3.project.individual.persistence.AccountRepository;
import sem3.project.individual.persistence.DTO.AccountTicketCountDTO;
import sem3.project.individual.persistence.entity.AccountEntity;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAccountsFunctionalityNormalTest {

    @Mock
    private AccountRepository mockRepo;
    @Mock
    private AccessToken mockToken;
    @InjectMocks
    private GetAccountsFunctionalityNormal useCase;

    @Test
    void getById_AccountExists_ReturnsResponseWithAccount() {
        // Arrange
        Long accountId = 1L;
        AccountEntity accountEntity = AccountEntity.builder()
                .id(accountId)
                .username("username")
                .email("email")
                .build();

        when(mockRepo.fetchById(accountId)).thenReturn(accountEntity);

        // Act
        Optional<GetAccountResponse> response = useCase.getById(accountId);

        // Assert
        assertTrue(response.isPresent());
        assertEquals(accountId, response.get().getAccount().getId());
        verify(mockRepo).fetchById(accountId);
    }

    @Test
    void getById_AccountDoesNotExist_ReturnsEmptyOptional() {
        // Arrange
        Long accountId = 1L;

        when(mockRepo.fetchById(accountId)).thenReturn(null);

        // Act
        Optional<GetAccountResponse> response = useCase.getById(accountId);

        // Assert
        assertFalse(response.isPresent());
        verify(mockRepo).fetchById(accountId);
    }

    @Test
    void getByUsername_AccountDoesNotExist_ReturnsNull()
    {
        String username = "Ambi";
        when(mockRepo.findByUsername(username)).thenThrow(NoSuchElementException.class);


        GetAccountResponse actual = useCase.getByUsername(username);

        assertNull(actual);
        verify(mockRepo).findByUsername(username);

    }

    @Test
    void getByUsername_AccountExistsAndValidToken_ReturnsResponseWithAccount() throws InvalidTokenException {
        // Arrange
        String username = "username";
        AccountEntity accountEntity = AccountEntity.builder()
                .id(1L)
                .username(username)
                .email("email")
                .build();

        when(mockRepo.findByUsername(username)).thenReturn(accountEntity);
        when(mockToken.hasRole(AccountRole.ADMIN.name())).thenReturn(true);

        // Act
        GetAccountResponse response = useCase.getByUsername(username);

        // Assert
        assertNotNull(response);
        assertEquals(username, response.getAccount().getUsername());
        verify(mockRepo).findByUsername(username);
        verify(mockToken).hasRole(AccountRole.ADMIN.name());
    }

    @Test
    void getByUsername_AccountExistsAndInvalidToken_ThrowsInvalidTokenException() throws InvalidTokenException {
        // Arrange
        String username = "username";
        AccountEntity accountEntity = AccountEntity.builder()
                .id(1L)
                .username(username)
                .email("email")
                .build();

        when(mockRepo.findByUsername(username)).thenReturn(accountEntity);
        when(mockToken.hasRole(AccountRole.ADMIN.name())).thenReturn(false);
        when(mockToken.getSubject()).thenReturn("Something different");  // Set the same username as the token subject

        // Act & Assert
        assertThrows(InvalidTokenException.class, () -> useCase.getByUsername(username));
        verify(mockRepo).findByUsername(username);
        verify(mockToken).hasRole(AccountRole.ADMIN.name());
        verify(mockToken).getSubject();
    }


    @Test
    void getAllAccounts() {
        // Arrange
        List<AccountEntity> accountEntities = List.of(
                AccountEntity.builder().id(1L).username("username1").email("email1").build(),
                AccountEntity.builder().id(2L).username("username2").email("email2").build(),
                AccountEntity.builder().id(3L).username("username3").email("email3").build()
        );

        when(mockRepo.findAll()).thenReturn(accountEntities);

        // Act
        GetMultipleAccountsResponse response = useCase.getAllAccounts();

        // Assert
        assertNotNull(response);
        assertEquals(3, response.getAccounts().size());
        verify(mockRepo).findAll();
    }

    @Test
    void getAccountsByTicketsBought() {
        // Arrange
        int threshold = 5;
        long longThreshold = threshold;
        List<AccountTicketCountDTO> accountTicketCountDTOs = List.of(
                new AccountTicketCountDTO("username1", 10),
                new AccountTicketCountDTO("username2", 8),
                new AccountTicketCountDTO("username3", 6)
        );

        when(mockRepo.getUsersByTicketCount(longThreshold)).thenReturn(accountTicketCountDTOs);

        // Act
        AccountRankingResponse<AccountTicketCountDTO> response = useCase.getAccountsByTicketsBought(threshold);

        // Assert
        assertNotNull(response);
        assertEquals(3, response.getRankings().size());
        verify(mockRepo).getUsersByTicketCount(longThreshold);
    }
}