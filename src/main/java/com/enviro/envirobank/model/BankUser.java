package com.enviro.envirobank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "bank_users")
public abstract class BankUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String identityNumber;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private String password;
    @Column(unique = true, name = "email",nullable = false)
    private String email;
    @Column(nullable = false, unique = true)
    private String userName;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles_details",
            joinColumns = {
                @JoinColumn(name = "user_id", referencedColumnName = "id")
            }, inverseJoinColumns = {
                    @JoinColumn(name = "role_id",referencedColumnName = "id")
            })

   private Set<UserRole> userRoles = new HashSet<>();


    private boolean locked = true;

    private boolean enabled =false;


}
