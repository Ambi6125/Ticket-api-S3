package sem3.project.individual.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sem3.project.individual.business.LoginFunctionality;
import sem3.project.individual.domain.login.LoginResponse;
import sem3.project.individual.misc.NotImplementedException;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController
{
    private LoginFunctionality loginFunc;
    @PostMapping
    public ResponseEntity<LoginResponse> login()
    {
        throw new NotImplementedException();
    }
}
