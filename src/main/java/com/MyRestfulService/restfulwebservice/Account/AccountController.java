package com.MyRestfulService.restfulwebservice.Account;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AccountController {
    private AccountDaoService accountDaoService;

    public AccountController(AccountDaoService accountDaoService) {
        this.accountDaoService = accountDaoService;
    }

    @GetMapping("/Accounts")
    public List<Account> retrieveAllUsers() {
        return accountDaoService.findAll();
    }

    @GetMapping("/Accounts/{id}")
    public ResponseEntity<EntityModel<Account>> retrieveUser(@PathVariable int id) {
        Account newAccount = accountDaoService.findOne(id);
        if(newAccount == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // HATEOAS
        EntityModel<Account> accountEntityModel = EntityModel.of(newAccount);
        WebMvcLinkBuilder LinkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        accountEntityModel.add(LinkTo.withRel("all-users"));

        return ResponseEntity.ok(accountEntityModel);
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

    @DeleteMapping("/Accounts/{id}")
    public void deleteUserById(@PathVariable int id) {
        Account deleteAccount = accountDaoService.deleteById(id);
        if(deleteAccount == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }

    @PutMapping("/Accounts/{id}")
    public void updateUserById(@PathVariable int id, @RequestParam String name) {
        Account updateAccount = accountDaoService.updateAccountName(id, name);
        if(updateAccount == null) throw new UserNotFoundException(String.format("ID[%s] not found", id));
    }
}
