package me.dgpark.springdataredis;

import lombok.Getter;
import me.dgpark.springdataredis.dto.response.AccountDetailResponse;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

// timeToLive : sec
@RedisHash(value = "accountDetail", timeToLive = 10)
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
