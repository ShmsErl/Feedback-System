package com.tobeto.feedback_system.services.abstracts;

import com.tobeto.feedback_system.core.results.DataResult;
import com.tobeto.feedback_system.core.results.Result;
import com.tobeto.feedback_system.models.concretes.User;
import com.tobeto.feedback_system.payload.requests.AddUserRequest;
import com.tobeto.feedback_system.payload.requests.UpdateUserRequest;
import com.tobeto.feedback_system.payload.responses.GetUserResponse;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    DataResult<List<GetUserResponse>> getUsers();

    DataResult<GetUserResponse> getUserById(Long id);

    User findByUsername(String username);

    Result addUser(AddUserRequest userRequest);
    Result signUp(User userRequest);

    Result updateUser(UpdateUserRequest updateUserRequest);

    Result deleteUser(Long userId);


}
