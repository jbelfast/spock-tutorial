package com.itexico.spock.tutorial.dto

/**
 * Created by Juan Malacrida on 21-Jul-16.
 */
class UserDTO {
    Long id;
    String firstName;
    String lastName;
    UserGroupDTO group;

    static class UserGroupDTO {
        Long id;
        String name;
    }
}

