package com.itexico.spock.tutorial.dao

import com.itexico.spock.tutorial.model.Group
import org.springframework.data.repository.CrudRepository

/**
 * Created by Juan Malacrida on 21-Jul-16.
 */
interface GroupRepository extends CrudRepository<Group, Long> {

}