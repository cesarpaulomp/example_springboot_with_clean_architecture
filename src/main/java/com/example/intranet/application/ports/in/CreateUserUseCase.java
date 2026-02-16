package com.example.intranet.application.ports.in;

import com.example.intranet.application.ports.in.input.CreateUserInput;

public interface CreateUserUseCase {
    public void createUser(CreateUserInput command);
}
