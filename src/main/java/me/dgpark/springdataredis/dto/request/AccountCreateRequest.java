package me.dgpark.springdataredis.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AccountCreateRequest {

    @NotNull
    @Min(1)
    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String username;

}
