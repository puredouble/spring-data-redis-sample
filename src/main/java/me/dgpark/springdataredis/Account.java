package me.dgpark.springdataredis;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String email;

    private Account(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public static Account createEntity(String username, String email) {
        return new Account(
                username,
                email
        );
    }

    public void updateEntity(String email, String username) {
        this.email = email;
        this.username = username;
    }
}
