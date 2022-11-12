package com.examplespringbootproject.BookMyShow.Model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="User")
public class UserEntity {
    //attributes or members
    @Id //make it primary
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment in individual class
    private int id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="mobile",nullable = false)
    private String mobile;

    //mapping relation
    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL) //user<->TicketEntity
    private List<TicketEntity> listOfTicketEntity;

    //list of ticketEntity
    //pending part-done
//done
}
