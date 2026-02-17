package com.example.intranet.infra.dto;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public record UserInfo(String id, List<? extends GrantedAuthority> roles) {
    
}
