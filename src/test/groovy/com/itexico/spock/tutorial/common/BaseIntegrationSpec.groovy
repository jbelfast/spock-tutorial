package com.itexico.spock.tutorial.common

import com.itexico.spock.tutorial.SpockTutorialApplication
import groovy.sql.Sql
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestPropertySource
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by Juan Malacrida on 02-Aug-16.
 */
@SpringApplicationConfiguration(classes = [SpockTutorialApplication.class])
@WebIntegrationTest(randomPort = true)
@TestPropertySource(["/application.properties"])
@DirtiesContext
abstract class BaseIntegrationSpec extends Specification {
    @Shared
    def sql = Sql.newInstance("jdbc:h2:mem:spock-tutorial;MODE=MySQL", "sa", "")

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