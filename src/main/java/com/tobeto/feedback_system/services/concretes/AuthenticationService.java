package com.tobeto.feedback_system.services.concretes;



import com.tobeto.feedback_system.core.constants.MessageConstants;
import com.tobeto.feedback_system.core.exceptions.InvalidPasswordException;
import com.tobeto.feedback_system.core.exceptions.UserNotEnabledException;
import com.tobeto.feedback_system.core.results.Result;
import com.tobeto.feedback_system.core.results.SuccessResult;
import com.tobeto.feedback_system.models.concretes.Role;
import com.tobeto.feedback_system.models.concretes.User;
import com.tobeto.feedback_system.payload.requests.SignInRequest;
import com.tobeto.feedback_system.payload.requests.SignUpRequest;
import com.tobeto.feedback_system.payload.responses.JwtAuthResponse;
import com.tobeto.feedback_system.security.service.JwtService;
import com.tobeto.feedback_system.services.abstracts.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final IUserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public Result signup(SignUpRequest request) throws Exception {
        var user = User
                .builder()
                .name(request.name())
                .surname(request.surname())
                .username(request.username())
                .birthDate(request.birthDate())
                .password(request.password())
                .role(Role.USER)
                .isEnabled(true)
                .build();


        userService.signUp(user);

        return new SuccessResult(MessageConstants.SIGNUP_SUCCESS.getMessage());
    }


    public JwtAuthResponse signin(SignInRequest request) {
        var user = userService.findByUsername(request.username());

        if (!user.isEnabled()) {
            throw new UserNotEnabledException(MessageConstants.USER_NOT_ENABLED.getMessage());
        }
        if (!passwordEncoder.matches(request.password(), user.getPassword()))
            throw new InvalidPasswordException(MessageConstants.INVALID_PASSWORD.getMessage());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        var jwtToken = jwtService.generateToken(user);

        return JwtAuthResponse.builder().token(jwtToken).build();
    }
}
