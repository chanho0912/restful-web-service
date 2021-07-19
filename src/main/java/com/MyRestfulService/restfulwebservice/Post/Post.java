package com.MyRestfulService.restfulwebservice.Post;

import com.MyRestfulService.restfulwebservice.Account.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    // Account -> Post
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Account account;
}
