package com.ys.auction.domain.users;

import com.ys.auction.domain.BaseTimeEntity;
import com.ys.auction.utils.EncryptionUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String nickname;

    private String email;

    private String password;

    private String phone;

    @Builder
    public Users(String nickname, String email, String password, String phone) {
        this.nickname = nickname;
        this.email = email;
        this.password = EncryptionUtils.encryptSHA256(password);
        this.phone = phone;
    }
}
