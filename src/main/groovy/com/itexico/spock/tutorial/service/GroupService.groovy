package com.itexico.spock.tutorial.service

import com.itexico.spock.tutorial.dto.GroupDTO
import com.itexico.spock.tutorial.model.Group

/**
 * Created by Juan Malacrida on 22-Jul-16.
 */
interface GroupService {

    List<Group> getAll()

    Group getById(Long id)

    Group createOrUpdate(Group group)

    void delete(Long id)

    List<GroupDTO> toDTO(List<Group> groups)

    GroupDTO toDTO(Group group)

    boolean exists(Long id)
}