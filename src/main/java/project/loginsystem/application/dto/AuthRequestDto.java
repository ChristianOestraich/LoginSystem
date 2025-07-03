package project.loginsystem.application.dto;

import lombok.*;

@Getter
@Setter
public class AuthRequestDto {
    private String username;
    private String password;
}