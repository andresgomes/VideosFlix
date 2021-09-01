package br.com.videos.controller;

import br.com.videos.config.secutiry.TokenServices;
import br.com.videos.controller.dto.TokenDto;
import br.com.videos.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = {"/auth","/")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenServices tokenServices;

    @PostMapping
    public ResponseEntity<TokenDto> autentication(@RequestBody @Valid LoginForm form){
        UsernamePasswordAuthenticationToken dataLogin = form.converter();
        try {
            Authentication authentication = authenticationManager.authenticate(dataLogin);
            String token = tokenServices.tokenGenerate(authentication);
            return ResponseEntity.ok(new TokenDto(token, "bearer"));
        }catch (AuthenticationException e){
            return ResponseEntity.ok().build();
        }
    }
}
