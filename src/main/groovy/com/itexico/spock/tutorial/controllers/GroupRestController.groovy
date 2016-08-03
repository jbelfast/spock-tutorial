package com.itexico.spock.tutorial.controllers

import com.itexico.spock.tutorial.dto.GroupDTO
import com.itexico.spock.tutorial.exceptions.CustomException
import com.itexico.spock.tutorial.model.Group
import com.itexico.spock.tutorial.service.GroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
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
        if (!groupService.exists(id))
            throw new CustomException("...Group with id: $id does not exist. Thus, it cannot be retrieved.")
        groupService.toDTO(groupService.getById(id))
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    GroupDTO update(@RequestBody Group group) {
        groupService.toDTO(groupService.createOrUpdate(group))
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    GroupDTO delete(@PathVariable Long id) {
        if (!groupService.exists(id))
            throw new CustomException("Group with id: $id does not exist. Thus, it cannot be deleted.")
        def dto = groupService.toDTO(groupService.getById(id))
        groupService.delete(id)
        dto
    }
}
