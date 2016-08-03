package com.itexico.spock.tutorial.common

import com.itexico.spock.tutorial.SpockTutorialApplication
import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by Juan Malacrida on 02-Aug-16.
 */
@SpringApplicationConfiguration(classes = [SpockTutorialApplication.class])
@WebIntegrationTest(randomPort = true)
@DirtiesContext
abstract class BaseIntegrationSpec extends Specification {

    @Shared
    Sql sql = null

    @Value('${spring.datasource.url}')
    String datasourceUrl

    @Value('${spring.datasource.username}')
    String datasourceUsername

    @Value('${spring.datasource.password}')
    String datasourcePassword

    @Value('${spring.datasource.driverClassName}')
    String datasourceDriverClassName

    def setup() {
        if (sql == null) {
            println "initializing sql once"
            sql = Sql.newInstance(datasourceUrl, datasourceUsername, datasourcePassword)
            sql.execute("CREATE TABLE IF NOT EXISTS GROUPS (ID INT PRIMARY KEY, NAME VARCHAR)")
            sql.execute("CREATE TABLE IF NOT EXISTS USERS (ID INT PRIMARY KEY, FIRST_NAME VARCHAR, LAST_NAME VARCHAR, GROUP_ID INT)")
            sql.execute("ALTER TABLE IF EXISTS USERS ADD FOREIGN KEY (GROUP_ID) REFERENCES GROUPS(ID)")
        } else {
            println "not initializing sql"
        }
    }

    def cleanupSpec() {
        sql.execute("DROP TABLE IF EXISTS USERS")
        sql.execute("DROP TABLE IF EXISTS GROUPS")
    }
}