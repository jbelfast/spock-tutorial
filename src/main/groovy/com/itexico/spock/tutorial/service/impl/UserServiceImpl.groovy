package com.itexico.spock.tutorial.service.impl

import com.itexico.spock.tutorial.dao.UserRepository
import com.itexico.spock.tutorial.dto.UserDTO
import com.itexico.spock.tutorial.model.User
import com.itexico.spock.tutorial.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Juan Malacrida on 21-Jul-16.
 */
@Service
class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository

    @Override
    List<User> getAll() {
        repository.findAll().asList()
    }

    @Override
    User getById(Long id) {
        repository.findOne(id)
    }

    @Override
    List<User> getByLastName(String lastName) {
        repository.findByLastName(lastName)
    }

    @Override
    User createOrUpdate(User user) {
        repository.save(user)
    }

    @Override
    void delete(Long id) {
        repository.delete(id)
    }

    List<UserDTO> toDTO(List<User> users) {
        def userDTOs = []
        users.each { userDTOs << toDTO(it) }
        userDTOs
    }

    UserDTO toDTO(User user) {
        new UserDTO(id: user.id, firstName: user.firstName, lastName: user.lastName, group: new UserDTO.UserGroupDTO(id: user.group.id, name: user.group.name))
    }

    @Override
    boolean exists(Long id) {
        repository.exists(id)
    }
}
