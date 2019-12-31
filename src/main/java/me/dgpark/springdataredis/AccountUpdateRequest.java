package me.dgpark.springdataredis;

import lombok.Data;

@Data
public class AccountUpdateRequest {

    private Long id;

    private String username;

    private String email;

}
