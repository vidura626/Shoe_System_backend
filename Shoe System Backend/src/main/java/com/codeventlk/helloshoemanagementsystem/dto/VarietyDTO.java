package com.codeventlk.helloshoemanagementsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VarietyDTO implements SuperDTO{
    @NotNull(message = "Variety Code Should Be not null.")
    private String varietyCode;
    @NotNull(message = "Variety Description Should Be not null.")
    private String varietyDesc;
}
