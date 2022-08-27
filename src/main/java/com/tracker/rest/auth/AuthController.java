package com.tracker.rest.auth;

import com.tracker.config.security.jwt.JwtProvider;
import com.tracker.db.entity.UserEntity;
import com.tracker.exception.UserNotFoundException;
import com.tracker.rest.auth.model.AuthSigninRequest;
import com.tracker.rest.auth.model.AuthSigninResponse;
import com.tracker.rest.auth.model.AuthSignupRequest;
import com.tracker.rest.auth.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController()
@Validated
public class AuthController {
    private final UserService userService;
    private final JwtProvider jwtProvider;

    public AuthController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("auth/sign-up")
    public User registerUser(@Valid @RequestBody AuthSignupRequest request) {
        var u = new UserEntity();
        u.setPassword(request.getPassword());
        u.setEmail(request.getEmail());
        u.setName(request.getName());

        return userService.create(u);
    }

    @PostMapping("auth/sign-in")
    public AuthSigninResponse auth(@Valid @RequestBody AuthSigninRequest request) {
        var userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());

        if (userEntity == null) {
            throw new UserNotFoundException();
        }

        var token = jwtProvider.generateToken(userEntity.getEmail());

        var u = User.toModel(userEntity);
        return new AuthSigninResponse(token, u);
    }
}
