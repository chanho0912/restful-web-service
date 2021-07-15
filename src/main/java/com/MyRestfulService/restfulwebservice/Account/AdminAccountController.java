package com.MyRestfulService.restfulwebservice.Account;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminAccountController {
    private AccountDaoService accountDaoService;

    public AdminAccountController(AccountDaoService accountDaoService) {
        this.accountDaoService = accountDaoService;
    }

    @GetMapping("/Accounts")
    public MappingJacksonValue retrieveAllUsers() {
        List<Account> users = accountDaoService.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinAt", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("AccountInfo", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/v1/Accounts/{id}")
    public MappingJacksonValue retrieveUserVer1(@PathVariable int id) {
        Account newAccount = accountDaoService.findOne(id);
        if(newAccount == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinAt", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("AccountInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(newAccount);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/v2/Accounts/{id}")
    public MappingJacksonValue retrieveUserVer2(@PathVariable int id) {
        Account newAccount = accountDaoService.findOne(id);
        if(newAccount == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // newAccount -> AccountVer2
        AccountVer2 accountVer2 = new AccountVer2();
        BeanUtils.copyProperties(newAccount, accountVer2);
        accountVer2.setGrade("VIP");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinAt", "grade");

        FilterProvider filters = new SimpleFilterProvider().addFilter("AccountInfoV2", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(accountVer2);
        mapping.setFilters(filters);

        return mapping;
    }

    @PostMapping("/Accounts")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account user) {
        Account newAccount = accountDaoService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAccount.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
