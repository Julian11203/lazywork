package com.lazywork.User;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    Integer id;
    String username;
    String password;
    String firstname;
    String lastname;
    Role role;
}
