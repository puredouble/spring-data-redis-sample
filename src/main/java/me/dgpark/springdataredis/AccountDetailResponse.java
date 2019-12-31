package me.dgpark.springdataredis;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Data
@NoArgsConstructor
public class AccountDetailResponse {

    @Id
    private Long id;

    private String username;

    private String email;

    public AccountDetailResponse(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.email = account.getEmail();
    }

}
