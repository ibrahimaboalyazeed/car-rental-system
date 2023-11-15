package com.global.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "client")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    @Pattern(regexp = "\\d{10}")
    private String phoneNumber;


    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id",unique = true)
    @NotNull(message = "app user is mandatory")
    private AppUser user;


}
