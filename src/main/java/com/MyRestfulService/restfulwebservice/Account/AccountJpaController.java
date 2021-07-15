package com.MyRestfulService.restfulwebservice.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")
public class AccountJpaController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/Accounts")
    public List<Account> retrieveAllUsers() {
        return accountRepository.findAll();
    }

    @GetMapping("/Account/{id}")
    public ResponseEntity<EntityModel<Account>> retrieveUser(@PathVariable int id) {
        Optional<Account> account = accountRepository.findById(id);
        if(account.isEmpty()){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        EntityModel<Account> accountEntityModel = EntityModel.of(account.get());
        WebMvcLinkBuilder LinkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        accountEntityModel.add(LinkTo.withRel("find-all-users"));

        return ResponseEntity.ok(accountEntityModel);
    }

    @DeleteMapping("/Account/{id}")
    public void deleteAccount(@PathVariable int id) {
        accountRepository.deleteById(id);
    }

    @PostMapping("/Account")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {
        Account newAccount = accountRepository.save(account);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAccount.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
