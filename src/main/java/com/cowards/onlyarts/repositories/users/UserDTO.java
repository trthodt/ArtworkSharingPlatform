package com.cowards.onlyarts.repositories.users;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

    private String userId;
    private String roleId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private Timestamp joinDate;
    private String bio;
    private String status;
    private String password;
}
