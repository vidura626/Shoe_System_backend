package com.codeventlk.helloshoemanagementsystem.secureAndResponse.secure;

import com.codeventlk.helloshoemanagementsystem.Enum.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUp {
    @Null(message = "Id generate by  program")
    private String id;
    @NotNull(message = "Email cannot be null")
    @Email
    private String email;
    @NotNull(message = "Password cannot be null")
    private String password;
    @NotNull(message = "Role cannot be null")
    private Role role;
}
