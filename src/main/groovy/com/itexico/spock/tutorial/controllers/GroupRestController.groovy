package com.itexico.spock.tutorial.controllers

import com.itexico.spock.tutorial.dto.GroupDTO
import com.itexico.spock.tutorial.service.GroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Juan Malacrida on 21-Jul-16.
 */
@RestController
@RequestMapping("/group")
class GroupRestController {

    @Autowired
    private GroupService groupService

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    List<GroupDTO> getAll() {
        groupService.toDTO(groupService.getAll())
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    GroupDTO getById(@PathVariable Long id) {
        groupService.toDTO(groupService.getById(id))
    }
}
