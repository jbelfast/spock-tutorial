package com.itexico.spock.tutorial.dao

import com.itexico.spock.tutorial.common.BaseIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by Juan Malacrida on 02-Aug-16.
 */
class UserRepositorySpec extends BaseIntegrationSpec {

    @Autowired
    UserRepository userRepository;



    def "test findByLastName"() {
        given: "There are two groups and three users stored in the repository. Two of those users have the same last name"

        sql.execute """INSERT INTO GROUPS (ID, NAME) VALUES (1, 'G1')"""
        sql.execute """INSERT INTO GROUPS (ID, NAME) VALUES (2, 'G2')"""

        sql.execute """INSERT INTO USERS (ID, FIRST_NAME, LAST_NAME, GROUP_ID) VALUES (1, 'F10', 'L10', 1) """
        sql.execute """INSERT INTO USERS (ID, FIRST_NAME, LAST_NAME, GROUP_ID) VALUES (2, 'F11', 'L10', 1) """
        sql.execute """INSERT INTO USERS (ID, FIRST_NAME, LAST_NAME, GROUP_ID) VALUES (3, 'F20', 'L20', 2) """

        when: "I find one user by last name (unique last name)"

        def users = userRepository.findByLastName("L20")

        then: "I get that single user"
        users.size() == 1


    }
}
