package com.yeqin.pims.domain;

import lombok.Data;

//保存用户信息
@Data
public class User {
    private Long id;   //用户信息的ID
    private String username;  //账号
    private String password;  //密码
}
