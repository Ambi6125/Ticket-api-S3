package sem3.project.individual.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sem3.project.individual.business.CreateAccountFunctionality;
import sem3.project.individual.business.DeleteAccountsFunctionality;
import sem3.project.individual.business.GetMultipleAccountsFunctionality;
import sem3.project.individual.business.implementors.GetAllAccountsImplementation;
import sem3.project.individual.domain.accounts.CreateAccountRequest;
import sem3.project.individual.domain.accounts.CreateAccountResponse;
import sem3.project.individual.domain.accounts.GetAllAccountsResponse;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController
{
    private final CreateAccountFunctionality createFunctionality;
    private final GetMultipleAccountsFunctionality getAllFunctionality;
    private final DeleteAccountsFunctionality deleteAccountsFunctionality;

    @GetMapping
    public ResponseEntity<GetAllAccountsResponse> getAll()
    {
        GetAllAccountsResponse response = getAllFunctionality.getAllAccounts();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody CreateAccountRequest request)
    {
        CreateAccountResponse response = createFunctionality.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable int id)
    {
        deleteAccountsFunctionality.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
