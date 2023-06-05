package sem3.project.individual.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sem3.project.individual.business.LoginFunctionality;
import sem3.project.individual.business.exceptions.InvalidCredentialsException;
import sem3.project.individual.domain.login.LoginRequest;
import sem3.project.individual.domain.login.LoginResponse;
import sem3.project.individual.misc.NotImplementedException;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"}, allowedHeaders = {"Accept", "Content-Type"})
public class LoginController
{

    private final LoginFunctionality loginFunc;
    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request)
    {
        try
        {
            var response = loginFunc.login(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (InvalidCredentialsException e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
