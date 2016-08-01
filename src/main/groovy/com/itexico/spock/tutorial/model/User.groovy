package com.itexico.spock.tutorial.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 * Created by Juan Malacrida on 21-Jul-16.
 */
@Entity
@Table(name = "USERS")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String firstName;
    String lastName;
    @ManyToOne
    Group group;
}
