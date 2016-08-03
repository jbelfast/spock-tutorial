package com.itexico.spock.tutorial.controllers

import com.itexico.spock.tutorial.dto.GroupDTO
import com.itexico.spock.tutorial.exceptions.CustomException
import com.itexico.spock.tutorial.model.Group
import com.itexico.spock.tutorial.model.User
import com.itexico.spock.tutorial.service.GroupService
import spock.lang.Specification

/**
 *  Created by Juan Malacrida on 02/08/2016.
 */
class GroupRestControllerSpec extends Specification {
    def "GetById"() {
        given: "There exists two groups in the repository, both of them have users assigned"

        def user1 = new User(id: 1L, firstName: "user1", lastName: "last1")
        def user2 = new User(id: 2L, firstName: "user2", lastName: "last2")
        def user3 = new User(id: 3L, firstName: "user3", lastName: "last3")

        def group1 = new Group(id: 1L, name: "group1", users: [user1, user2])
        def group2 = new Group(id: 2L, name: "group2", users: [user3])

        def groups = [group1, group2]

        def groupService = Mock(GroupService)

        groupService.exists(_ as Long) >> { Long id -> groups[id.intValue() - 1] != null }
        groupService.getById(_ as Long) >> { Long id -> groups[id.intValue() - 1] }
        groupService.toDTO(_ as Group) >> { Group group -> new GroupDTO(id: group.id, name: group.name) }

        def groupRestController = new GroupRestController(groupService: groupService)

        when: "I attempt to retrieve one existing group"
        def groupDto = groupRestController.getById(group1.id)

        then: "I get it properly"
        noExceptionThrown()
        groupDto != null
        groupDto.id == group1.id
        groupDto.name == group1.name

        when: "I attempt to retrieve a non existing group"
        def groupDto3 = groupRestController.getById(3L)

        then: "I get an exception"
        def ex = thrown(CustomException)
        ex.message.contains("does not exist")
    }
}
