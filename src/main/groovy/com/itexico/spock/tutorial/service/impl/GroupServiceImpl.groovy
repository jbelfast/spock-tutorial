package com.itexico.spock.tutorial.service.impl

import com.itexico.spock.tutorial.dao.GroupRepository
import com.itexico.spock.tutorial.dto.GroupDTO
import com.itexico.spock.tutorial.exceptions.CustomException
import com.itexico.spock.tutorial.model.Group
import com.itexico.spock.tutorial.model.User
import com.itexico.spock.tutorial.service.GroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Juan Malacrida on 22-Jul-16.
 */
@Service
class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository

    @Override
    List<Group> getAll() {
        groupRepository.findAll().asList()
    }

    @Override
    Group getById(Long id) {
        groupRepository.findOne(id)
    }

    @Override
    Group createOrUpdate(Group group) {
        groupRepository.save(group)
    }

    @Override
    void delete(Long id) {
        if (!exists(id))
            throw new CustomException("Group id $id does not exists.")
        def u = []
        getById(id).users.each { u << it.id }
        if (u.isEmpty())
            groupRepository.delete(id)
        else
            throw new CustomException("Group id $id has these users ${u.join(", ")} assigned. First, delete those users.")
    }

    @Override
    List<GroupDTO> toDTO(List<Group> groups) {
        def list = []
        groups.each { list << toDTO(it) }
        list
    }

    @Override
    GroupDTO toDTO(Group group) {
        def groupDTO = new GroupDTO(id: group.id, name: group.name, users: [])
        group.users.each { groupDTO.users << toGroupUserDTO(it) }
        groupDTO
    }

    @Override
    boolean exists(Long id) {
        groupRepository.exists(id)
    }

    private static GroupDTO.GroupUserDTO toGroupUserDTO(User user) {
        new GroupDTO.GroupUserDTO(id: user.id, firstName: user.firstName, lastName: user.lastName)
    }
}
