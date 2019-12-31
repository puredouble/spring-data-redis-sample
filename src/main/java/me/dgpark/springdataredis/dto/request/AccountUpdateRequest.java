package me.dgpark.springdataredis.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AccountUpdateRequest {

    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

}
