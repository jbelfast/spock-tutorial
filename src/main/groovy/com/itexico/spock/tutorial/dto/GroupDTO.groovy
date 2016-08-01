package com.itexico.spock.tutorial.dto

/**
 * Created by Juan Malacrida on 21-Jul-16.
 */
class GroupDTO {
    Long id;
    String name;
    List<GroupUserDTO> users

    static class GroupUserDTO {
        Long id;
        String firstName;
        String lastName;
    }
}
