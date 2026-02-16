package com.example.intranet.application.ports.out;

import com.example.intranet.application.ports.out.output.SecurityTokenOutput;

public interface SecurityTokenGeneration {
    SecurityTokenOutput generateToken(String content);
}