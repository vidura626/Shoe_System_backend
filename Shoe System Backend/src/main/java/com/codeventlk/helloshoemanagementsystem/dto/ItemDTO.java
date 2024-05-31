package com.codeventlk.helloshoemanagementsystem.dto;

import com.codeventlk.helloshoemanagementsystem.Enum.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO implements SuperDTO{
    @Null(message = "Item generate by program")
    private String itemCode;
    @NotBlank(message = "Item Description cannot be blank")
    private String itemDesc;
    @NotNull(message = "Propic cannot be null")
    private String pic;
    private String genderCode;
    private String occasionCode;
    private String varietyCode;
}
