package com.itexico.spock.tutorial.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

import javax.persistence.OneToMany
import javax.persistence.Table

/**
 * Created by Juan Malacrida on 21-Jul-16.
 */
@Entity
@Table(name = "GROUPS")
class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    Long id;
    @Column(name = "NAME")
    String name;
    @OneToMany(mappedBy = "group")
    List<User> users;
}
