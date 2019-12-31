package me.dgpark.springdataredis;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@RedisHash("accountDetail")
@Getter
public class AccountDetailCache {

    @Id
    private Long id;

    private AccountDetailResponse response;

    public AccountDetailCache(Long id, AccountDetailResponse response) {
        this.id = id;
        this.response = response;
    }

}
