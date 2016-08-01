package com.itexico.spock.tutorial.service

import com.itexico.spock.tutorial.dto.UserDTO
import com.itexico.spock.tutorial.model.User

/**
 * Created by Juan Malacrida on 21-Jul-16.
 */
interface UserService {

    List<User> getAll()

    User getById(Long id)

    List<User> getByLastName(String lastName)

    User createOrUpdate(User user)

    void delete(Long id)

    List<UserDTO> toDTO(List<User> users)

    UserDTO toDTO(User user)
}