package com.itexico.spock.tutorial.service.impl

import com.itexico.spock.tutorial.dao.GroupRepository
import com.itexico.spock.tutorial.exceptions.CustomException
import com.itexico.spock.tutorial.model.Group
import com.itexico.spock.tutorial.model.User
import spock.lang.Specification

/**
 *  Created by Juan Malacrida on 02/08/2016.
 */
class GroupServiceImplSpec extends Specification {

    def "Delete group with users assigned"() {
        given: "There exists two groups in the repository, one of them has users assigned"

        def user1 = new User(id: 1L, firstName: "user1", lastName: "last1")
        def user2 = new User(id: 2L, firstName: "user2", lastName: "last2")

        def group1 = new Group(id: 1L, name: "group1", users: [user1, user2])
        def group2 = new Group(id: 2L, name: "group2", users: [])

        def groupRepository = Mock(GroupRepository)

        groupRepository.findOne(_ as Long) >> {
            Long id ->
                if (id == 1L) group1
                else if (id == 2L) group2
                else null
        }

        groupRepository.exists(1L) >> true
        groupRepository.exists(2L) >> true
        groupRepository.exists({ it >= 3L }) >> false

        def groupService = new GroupServiceImpl(groupRepository: groupRepository)

        when: "I attempt to delete a group that has users assigned"
        groupService.delete(1L)

        then: "An exception is thrown"
        def ex = thrown(CustomException)
        ex.message.contains("First, delete those users")

        when: "I attempt to delete a group that has not any user assigned"
        groupService.delete(2L)

        then: "That group is deleted"
        1 * groupRepository.delete(2L)

        when: "I attempt to delete a group that does not exists"
        groupService.delete(3L)

        then: "An exception is thrown"
        def ex2 = thrown(CustomException)
        ex2.message.contains("does not exists")
    }
}
