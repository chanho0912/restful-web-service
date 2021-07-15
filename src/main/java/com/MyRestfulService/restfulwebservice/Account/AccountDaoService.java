package com.MyRestfulService.restfulwebservice.Account;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AccountDaoService {
    private static List<Account> users = new ArrayList<>();
    private static int userCount = 3;
    static {
        users.add(new Account(1, "ChanHo", LocalDateTime.now(), "pass1", "7777"));
        users.add(new Account(2, "DaEun", LocalDateTime.now(), "pass2", "8888"));
        users.add(new Account(3, "SeHyun", LocalDateTime.now(), "pass3", "9999"));
    }

    public List<Account> findAll() {
        return users;
    }
    public Account save(Account user) {
        if(user.getId()==null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }
    public Account findOne(int id) {
        for (Account user : users) {
            if(user.getId()==id) return user;
        }
        return null;
    }
    public Account deleteById(int id) {
        Iterator<Account> AccountIterator = users.iterator();
        while(AccountIterator.hasNext()){
            Account user = AccountIterator.next();
            if(user.getId()==id){
                AccountIterator.remove();
                return user;
            }
        }
        return null;
    }
    public Account updateAccountName(int id, String updateName) {
        for (Account user : users) {
            if (user.getId() == id) {
                user.updateAccount(updateName);
                return user;
            }
        }
        return null;
    }
}
