package com.enviro.envirobank.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor

@Getter
@Setter
@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends BankUser {


}
