package com.dulich.dulich.service;

import com.dulich.dulich.dao.UserTempData;
import com.dulich.dulich.model.Account;

import org.springframework.stereotype.Service;

@Service
public class StoreDataService {
    private UserTempData userTempData;

    public UserTempData get() {
        return userTempData;
    }

    public void setUserTempData(Account account) {
        userTempData = new UserTempData(account);
    }

    public String getUsername() {
        return userTempData.getAccount().getUsername();
    }

    public void logOut() {
        userTempData = null;
    }
}
