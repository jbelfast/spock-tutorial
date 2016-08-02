package com.itexico.spock.tutorial.service.impl

import spock.lang.Specification

/**
 * Created by Juan Malacrida on 01-Aug-16.
 */
class UserServiceImplSpec2 extends Specification {
    def setup() {
    }

    def cleanup() {
    }

    def "Return users"() {
        given: "There are more than one users stored in the users repository"

        when: "I retrieve all of them"

        then: "I get exactly all the stored users"

        when: "I request for one user"

        then: "I get only one user"

        and: "I get exactly that requested user"

        when: "I request a non-existing user"

        then: "I get an exception"

    }

    def "Add users"() {
        given: "The users repository is empty"

        when: "I add one user"

        then: "That user is actually stored"

        and: "It can be retrieved"

    }

    def "Delete users"()    {
        given: "There are two users in the users repository"

        when : "I delete one of them"

        then : "There is only one user stored in the repository"

        and : "I can retrieve the remaining user"
    }

}
