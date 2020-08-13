package com.lwx.mybatis.domain;

import com.lwx.mybatis.enums.UserSexEnum;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {

    public User(String userName, String passWord, UserSexEnum userSex) {
        this.userName = userName;
        this.passWord = passWord;
        this.userSex = userSex;
    }

    private Long id;
    private String userName;
    private String passWord;
    private UserSexEnum userSex;
    private String nickName;
    private Long natureId;
    private Nature nature;

}
