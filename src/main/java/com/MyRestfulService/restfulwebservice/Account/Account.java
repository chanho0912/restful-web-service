package com.MyRestfulService.restfulwebservice.Account;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
//@JsonFilter("AccountInfo")
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
@Entity
public class Account {

    @Id @GeneratedValue
    private Integer id;

    @Size(min=2, message = "name은 2글자 이상 입력해 주세요.")
    @ApiModelProperty(notes = "사용자의 이름을 입력해 주세요")
    private String name;

    @Past
    @ApiModelProperty(notes = "사용자의 등록일을 입력해 주세요")
    private LocalDateTime joinAt;

//    @JsonIgnore
    @ApiModelProperty(notes = "사용자의 비밀번호를 입력해 주세요")
    private String password;

//    @JsonIgnore
    @ApiModelProperty(notes = "사용자의 주빈번호를 입력해 주세요")
    private String ssn;

    public int updateAccount(String updateName) {
        this.name = updateName;
        return this.id;
    }
}
