package io.github.vilginushki;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class User {
    String gender;
    String firstName;
    String lastName;
    String city;
    UUID loginUUID;
    LocalDateTime registrationDate;
}
