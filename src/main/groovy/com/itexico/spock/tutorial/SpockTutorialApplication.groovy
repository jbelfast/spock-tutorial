package com.itexico.spock.tutorial

import com.itexico.spock.tutorial.dao.GroupRepository
import com.itexico.spock.tutorial.dao.UserRepository
import com.itexico.spock.tutorial.model.Group
import com.itexico.spock.tutorial.model.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile

/**
 * Created by Juan Malacrida on 21-Jul-16.
 */
@SpringBootApplication
class SpockTutorialApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpockTutorialApplication.class);

    static void main(String[] args) {
        SpringApplication.run(SpockTutorialApplication.class)
    }

    @Bean
    @Profile(value = "dev")
    CommandLineRunner demo(GroupRepository groupRepository, UserRepository userRepository) {
        { args ->
            logger.info("running here!");

            def group1 = groupRepository.save(new Group(name: "group1"))
            def group2 = groupRepository.save(new Group(name: "group2"))

            userRepository.save(new User(firstName: "user1", lastName: "last1", group: group1))
            userRepository.save(new User(firstName: "user2", lastName: "last2", group: group1))
            userRepository.save(new User(firstName: "user3", lastName: "last3", group: group2))

        }
    }
}

