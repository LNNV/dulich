package com.dulich.dulich.dao;

import com.dulich.dulich.model.Account;

import lombok.Data;

@Data
public class UserTempData {
    private Account account;
    public UserTempData(Account account) {
        this.account = account;
    }
}
