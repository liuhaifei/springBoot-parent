package com.xinho.springboot.shardingJdbc.entity;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/614:06
 */
public class UserInfo {


    private Long userId;


    private String userName;


    private String account;


    private String password;


    public Long getUserId() {
        return userId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }


    public String getAccount() {
        return account;
    }


    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

}
