package com.itexico.spock.tutorial.dao

import com.itexico.spock.tutorial.model.User
import org.springframework.data.repository.CrudRepository

/**
 * Created by Juan Malacrida on 21-Jul-16.
 */
interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByLastName(String lastName);
}
