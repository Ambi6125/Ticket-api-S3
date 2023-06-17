package sem3.project.individual.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sem3.project.individual.business.CreateAccountFunctionality;
import sem3.project.individual.business.DeleteAccountsFunctionality;
import sem3.project.individual.business.GetAccountsFunctionality;
import sem3.project.individual.business.UpdateAccountFunctionality;
import sem3.project.individual.business.exceptions.InvalidTokenException;
import sem3.project.individual.configuration.security.auth.RequireAuthentication;
import sem3.project.individual.domain.accounts.*;
import sem3.project.individual.misc.UnexpectedResultException;
import sem3.project.individual.persistence.DTO.AccountTicketCountDTO;

import javax.annotation.security.RolesAllowed;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"}, allowedHeaders = {"Accept", "Content-Type", "Authorization"})
public class AccountController
{
    private final CreateAccountFunctionality createFunctionality;
    private final GetAccountsFunctionality getAccountFunctionality;
    private final UpdateAccountFunctionality updateAccountFunctionality;

    @GetMapping
    public ResponseEntity<GetMultipleAccountsResponse> getAll()
    {
        GetMultipleAccountsResponse response = getAccountFunctionality.getAllAccounts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GetAccountResponse> getById(@PathVariable Long id)
    {
        var response = getAccountFunctionality.getById(id);
        if(response.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok(response.get());
        }
    }

    @PostMapping
    public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody CreateAccountRequest request)
    {
        CreateAccountResponse response = createFunctionality.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/{username}") @RequireAuthentication @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<GetAccountResponse> getAccount(@PathVariable String username)
    {
        try
        {
            Optional<GetAccountResponse> responseOptional = Optional.of(getAccountFunctionality.getByUsername(username));

            return responseOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
        }
        catch (InvalidTokenException tokenException)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping @RequireAuthentication @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> updateAccount(@RequestBody UpdateAccountRequest request)
    {
        updateAccountFunctionality.update(request);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/statistics/rank/buyers") @RequireAuthentication @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<AccountRankingResponse<AccountTicketCountDTO>> getAccountsRankedByTicketsBought(@RequestParam int min)
    {
        var response = getAccountFunctionality.getAccountsByTicketsBought(min);
        return ResponseEntity.ok(response);
    }
}
