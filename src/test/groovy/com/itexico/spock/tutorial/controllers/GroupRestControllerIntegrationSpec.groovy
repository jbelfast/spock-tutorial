package com.itexico.spock.tutorial.controllers

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

/**
 * Created by Juan Malacrida on 02-Aug-16.
 */
class GroupRestControllerIntegrationSpec extends BaseRestControllerSpec {

    def "test getAll"() {
        given: "There are two groups and three users stored in the repository"

        sql.execute """INSERT INTO GROUPS (ID, NAME) VALUES (1, 'G1')"""
        sql.execute """INSERT INTO GROUPS (ID, NAME) VALUES (2, 'G2')"""

        sql.execute """INSERT INTO USERS (ID, FIRST_NAME, LAST_NAME, GROUP_ID) VALUES (1, 'F10', 'L10', 1) """
        sql.execute """INSERT INTO USERS (ID, FIRST_NAME, LAST_NAME, GROUP_ID) VALUES (2, 'F11', 'L11', 1) """
        sql.execute """INSERT INTO USERS (ID, FIRST_NAME, LAST_NAME, GROUP_ID) VALUES (3, 'F20', 'L20', 2) """

        when: "I retrieve all groups"
        def response = mockMvc.perform(get("/group/all")).andReturn().response

        then: "I get two groups"
        response.status == 200

        def content = response.contentAsString
        def json = jsonSlurper.parseText(content)
        json.size() == 2

        and: "Each value is correct"

        json[0].id == 1
        json[0].name == 'G1'

        json[1].id == 2
        json[1].name == 'G2'
    }

//    def "test getById"() {
//        given:
//
//        when:
//        // TODO implement stimulus
//        then:
//        // TODO implement assertions
//    }
//
//    def "test update"() {
//        given:
//
//        when:
//        // TODO implement stimulus
//        then:
//        // TODO implement assertions
//    }
//
//    def "test delete"() {
//        given:
//
//        when:
//        // TODO implement stimulus
//        then:
//        // TODO implement assertions
//    }
}
