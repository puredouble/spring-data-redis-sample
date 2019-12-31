package me.dgpark.springdataredis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dgpark.springdataredis.dto.request.AccountCreateRequest;
import me.dgpark.springdataredis.dto.request.AccountUpdateRequest;
import me.dgpark.springdataredis.dto.response.AccountCreateResponse;
import me.dgpark.springdataredis.dto.response.AccountDetailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountController {

    private final AccountDetailCacheRepository accountDetailCacheRepository;
    private final AccountRepository accountRepository;

    @GetMapping("account/{id}")
    public ResponseEntity accountDetail(@PathVariable Long id) {
        log.info("===== accountDetail start =====");

        Optional<AccountDetailCache> optional = accountDetailCacheRepository.findById(id);

        if (optional.isPresent()) {
            log.info("get account in redis : " + id);
            AccountDetailCache accountDetailCache = optional.get();
            return ResponseEntity.ok(accountDetailCache.getResponse());
        }

        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (!optionalAccount.isPresent()) {
            log.error("cannot found account id : " + id);
            throw new EntityNotFoundException("cannot found account id : " + id);
        }

        log.info("get account in database : " + id);
        Account account = optionalAccount.get();

        AccountDetailResponse accountDetailResponse = new AccountDetailResponse(account);

        AccountDetailCache accountDetailCache = new AccountDetailCache(id, accountDetailResponse);
        accountDetailCacheRepository.save(accountDetailCache);

        log.info("===== accountDetail end =====");
        return ResponseEntity.ok(accountDetailResponse);
    }

    @Transactional
    @PostMapping("account")
    public ResponseEntity accountCreate(@RequestBody AccountCreateRequest request) {
        Account account = Account.createEntity(
                request.getUsername(),
                request.getEmail()
        );

        Account save = accountRepository.save(account);

        AccountCreateResponse accountCreateResponse = new AccountCreateResponse(save.getId());

        return ResponseEntity.ok(accountCreateResponse);
    }

    @Transactional
    @PutMapping("account/{id}")
    public ResponseEntity accountUpdate(@PathVariable Long id, @RequestBody AccountUpdateRequest request) {
        Optional<Account> oAccount = accountRepository.findById(id);

        if (!oAccount.isPresent()) {
            throw new EntityNotFoundException("cannot found account id : " + id);
        }

        Account account = oAccount.get();

        account.updateEntity(
                request.getEmail(),
                request.getUsername()
        );

        accountDetailCacheRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
