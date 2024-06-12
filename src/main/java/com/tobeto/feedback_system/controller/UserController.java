package com.tobeto.feedback_system.controller;

import com.tobeto.feedback_system.core.results.DataResult;
import com.tobeto.feedback_system.core.results.Result;
import com.tobeto.feedback_system.payload.requests.AddUserRequest;
import com.tobeto.feedback_system.payload.requests.UpdateUserRequest;
import com.tobeto.feedback_system.payload.responses.GetUserResponse;
import com.tobeto.feedback_system.services.abstracts.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@CrossOrigin
public class UserController {

    private final IUserService userService;

    @GetMapping()
    public DataResult<List<GetUserResponse>> getAllUsers() {
        return userService.getUsers();
    }



    @GetMapping("/{id}")
    public DataResult<GetUserResponse> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Result createUser(@Valid @RequestBody AddUserRequest userRequest){

        return this.userService.addUser(userRequest);
    }



    @PutMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Result updateUser(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        return userService.updateUser(updateUserRequest);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Result deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }



}
