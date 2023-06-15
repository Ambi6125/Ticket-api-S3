package sem3.project.individual.business.implementors.accounts;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.project.individual.domain.accounts.UpdateAccountRequest;
import sem3.project.individual.domain.accounts.UpdateAccountResponse;
import sem3.project.individual.persistence.AccountRepository;
import sem3.project.individual.persistence.entity.AccountEntity;

import java.util.NoSuchElementException;

@ExtendWith(MockitoExtension.class)
class UpdateAccountFunctionalityNormalTest {

    @Mock
    private AccountRepository mockRepo;
    @InjectMocks
    private UpdateAccountFunctionalityNormal useCase;

    @Test
    void update_ValidRequest() {
        // Arrange
        UpdateAccountRequest request = UpdateAccountRequest.builder()
                .targetId(1L)
                .username("newUsername")
                .email("newEmail")
                .password("newPassword")
                .build();

        AccountEntity accountData = AccountEntity.builder()
                .id(1L)
                .username("oldUsername")
                .email("oldEmail")
                .password("oldPassword")
                .build();

        when(mockRepo.fetchById(request.getTargetId())).thenReturn(accountData);

        AccountEntity updatedAccount = AccountEntity.builder()
                .id(1L)
                .username("newUsername")
                .email("newEmail")
                .password("newPassword")
                .build();

        when(mockRepo.save(updatedAccount)).thenReturn(updatedAccount);

        useCase.update(request);

        // Assert
        verify(mockRepo).fetchById(request.getTargetId());
        verify(mockRepo).save(accountData);
    }

    @Test
    void update_InvalidAccountId_ThrowsNoSuchElementException() {
        // Arrange
        UpdateAccountRequest request = new UpdateAccountRequest(1L, "newUsername", "newEmail", "newPassword");

        when(mockRepo.fetchById(request.getTargetId())).thenReturn(null);

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> useCase.update(request));

        verify(mockRepo).fetchById(request.getTargetId());
    }

}
