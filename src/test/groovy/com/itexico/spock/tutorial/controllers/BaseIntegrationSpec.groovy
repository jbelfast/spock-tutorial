package com.itexico.spock.tutorial.controllers

import com.itexico.spock.tutorial.SpockTutorialApplication
import groovy.json.JsonSlurper
import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Shared
import spock.lang.Specification
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by Juan Malacrida on 02-Aug-16.
 */
@SpringApplicationConfiguration(SpockTutorialApplication.class)
@DirtiesContext
@WebAppConfiguration
abstract class BaseIntegrationSpec extends Specification {

    @Autowired
    WebApplicationContext webApp

    MockMvc mockMvc

    @Shared
    def sql = Sql.newInstance("jdbc:h2:mem:", "org.h2.Driver")

    @Shared
    def jsonSlurper = new JsonSlurper()

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApp).alwaysDo(print())build()
    }

    def setupSpec() {
        sql.execute("CREATE TABLE GROUPS (ID INT PRIMARY KEY, NAME VARCHAR)")
        sql.execute("CREATE TABLE USERS (ID INT PRIMARY KEY, FIRST_NAME VARCHAR, LAST_NAME VARCHAR, GROUP_ID INT)")
        sql.execute("ALTER TABLE USERS ADD FOREIGN KEY (GROUP_ID) REFERENCES GROUPS(ID)")
    }

    def cleanupSpec() {
        sql.execute("DROP TABLE USERS;")
        sql.execute("DROP TABLE GROUPS;")
    }
}