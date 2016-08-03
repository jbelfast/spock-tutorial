package com.itexico.spock.tutorial.controllers

import com.itexico.spock.tutorial.common.BaseIntegrationSpec
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Shared
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by Juan Malacrida on 02-Aug-16.
 */
abstract class BaseRestControllerSpec extends BaseIntegrationSpec {

    @Autowired
    WebApplicationContext webApp

    @Shared
    MockMvc mockMvc = null

    @Shared
    def jsonSlurper = new JsonSlurper()

    def setup() {
        if (mockMvc == null) {
            println "initializing mockMVC once"
            mockMvc = MockMvcBuilders.webAppContextSetup(webApp).alwaysDo(print()) build()
        }
    }
}