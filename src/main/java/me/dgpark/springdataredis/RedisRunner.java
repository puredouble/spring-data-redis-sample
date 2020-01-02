package me.dgpark.springdataredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RedisRunner implements ApplicationRunner {

//    @Autowired
//    private StringRedisTemplate redisTemplate;

    @Autowired
    private AccountDetailCacheRepository accountDetailCacheRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
//        ValueOperations<String, String> values = redisTemplate.opsForValue();
//        values.set("name", "dgpark");
//        values.set("framework", "spring");
//        values.set("message", "hello world");
//
//        Account account = new Account();
//        account.setId("myId");
//        account.setEmail("dgpark@nextculture.kr");
//        account.setUsername("dgpark");
//
//        accountRepository.save(account);
//
//        Optional<Account> byId = accountRepository.findById(account.getId());
//        System.out.println(byId.orElse(new Account()).getUsername());
//        System.out.println(byId.orElse(new Account()).getEmail());

        accountDetailCacheRepository.deleteAll();

        Account account1 = Account.createEntity("user1", "email1");
        Account account2 = Account.createEntity("user2", "email2");
        Account account3 = Account.createEntity("user3", "email3");

        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);
    }

}

