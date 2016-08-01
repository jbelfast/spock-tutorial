package com.itexico.spock.tutorial.service.impl

import spock.lang.Specification

/**
 * Created by Juan Malacrida on 01-Aug-16.
 */
class UserServiceImplSpec extends Specification {
    def setup() {
    }

    def cleanup() {
    }

    def "Return users"() {
        given: "There are more than one users stored in the data base"

        when: "I retrieve all of them"

        then: "I get exactly all the stored users"

        when: "I request for one user"

        then: "I get only one user"

        and: "I get exactly that requested user"

        when: "I request a non-existing user"

        then: "I get an exception"

    }

    def "Add users"() {
        given: "The users table is empty"


    }


}
