package com.MyRestfulService.restfulwebservice.Account;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@JsonFilter("AccountInfoV2")
public class AccountVer2 extends Account{
    private String grade;
}
