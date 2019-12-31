package me.dgpark.springdataredis.dto.response;

import lombok.Data;

@Data
public class AccountCreateResponse {

    private Long id;

    public AccountCreateResponse(Long id) {
        this.id = id;
    }

}
