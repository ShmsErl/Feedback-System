package com.tobeto.feedback_system.services.concretes;

import com.tobeto.feedback_system.core.constants.MessageConstants;
import com.tobeto.feedback_system.core.exceptions.AlreadyExistsException;
import com.tobeto.feedback_system.core.exceptions.NotFoundException;
import com.tobeto.feedback_system.core.mappers.ModelMapperService;
import com.tobeto.feedback_system.core.results.DataResult;
import com.tobeto.feedback_system.core.results.Result;
import com.tobeto.feedback_system.core.results.SuccessDataResult;
import com.tobeto.feedback_system.core.results.SuccessResult;
import com.tobeto.feedback_system.models.concretes.Role;
import com.tobeto.feedback_system.models.concretes.User;
import com.tobeto.feedback_system.payload.requests.AddUserRequest;
import com.tobeto.feedback_system.payload.requests.UpdateUserRequest;
import com.tobeto.feedback_system.payload.responses.GetUserResponse;
import com.tobeto.feedback_system.repository.UserRepository;
import com.tobeto.feedback_system.services.abstracts.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserManager implements IUserService {

    private final UserRepository userRepository;
    private final ModelMapperService mapperService;
    private final PasswordEncoder passwordEncoder;



    @Override
    public DataResult<List<GetUserResponse>> getUsers() {
       List<GetUserResponse> responses = this.userRepository.findAll()
                .stream().
                map(user -> this.mapperService.forResponse().map(user,GetUserResponse.class)).toList();
        return new SuccessDataResult<>(responses, MessageConstants.GET_ALL.getMessage());
    }

    @Override
    public DataResult<GetUserResponse> getUserById(Long id) {

        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageConstants.USER.getMessage() + MessageConstants.NOT_FOUND.getMessage()));
        GetUserResponse response = this.mapperService.forResponse().map(user, GetUserResponse.class);

        return new SuccessDataResult<>(response, MessageConstants.GET.getMessage());
    }

    @Override
    public User findByUsername(String username) {

        return this.userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(MessageConstants.NOT_FOUND.getMessage()));
    }

    @Override
    public Result addUser(AddUserRequest userRequest) {
        if(this.userRepository.existsByUsername(userRequest.getUsername())){
            throw new AlreadyExistsException(MessageConstants.ALREADY_EXISTS.getMessage());
        }
        User user= this.mapperService.forRequest().map(userRequest, User.class);
        userRequest.setPassword(this.passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(Role.USER);
        this.userRepository.save(user);
        return new SuccessResult(MessageConstants.ADD.getMessage());
    }

    @Override
    public Result signUp(User userRequest) {
        if(this.userRepository.existsByUsername(userRequest.getName())){
            throw new AlreadyExistsException(MessageConstants.ALREADY_EXISTS.getMessage());
        }
        userRequest.setPassword(this.passwordEncoder.encode(userRequest.getPassword()));
        this.userRepository.save(userRequest);
        return new SuccessResult(MessageConstants.ADD.getMessage());
    }




    @Override
    public Result updateUser(UpdateUserRequest updateUserRequest) {
        User getUser = this.userRepository.findById(updateUserRequest.getId())
                .orElseThrow(() -> new NotFoundException(MessageConstants.USER.getMessage() + MessageConstants.NOT_FOUND.getMessage()));
        User user = this.mapperService.forRequest().map(updateUserRequest, User.class);
        user.setPassword(this.passwordEncoder.encode(getUser.getPassword()));
        user.setUsername(getUser.getUsername());
        user.setRole(getUser.getRole());
        this.userRepository.save(user);


        return new SuccessResult(MessageConstants.UPDATE.getMessage());
    }

    @Override
    public Result deleteUser(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException(MessageConstants.ID_NOT_FOUND.getMessage()));
        this.userRepository.deleteById(userId);

        return new SuccessResult(MessageConstants.DELETE.getMessage());
    }
}
