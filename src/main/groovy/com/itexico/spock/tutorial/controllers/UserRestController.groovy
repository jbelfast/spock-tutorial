package com.itexico.spock.tutorial.controllers

import com.itexico.spock.tutorial.dto.UserDTO
import com.itexico.spock.tutorial.exceptions.CustomException
import com.itexico.spock.tutorial.model.User
import com.itexico.spock.tutorial.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Juan Malacrida on 21-Jul-16.
 */
@RestController
@RequestMapping("/user")
class UserRestController {

    @Autowired
    private UserService userService

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    List<UserDTO> getAll(@RequestParam(value = "lastName", defaultValue = "") String lastName) {
        if (lastName == "") userService.toDTO(userService.getAll())
        else userService.toDTO(userService.getByLastName(lastName))
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    UserDTO getById(@PathVariable Long id) {
        if (!userService.exists(id))
            throw new CustomException("User with id: $id does not exist. Thus, it cannot be retrieved.")
        userService.toDTO(userService.getById(id))
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    UserDTO update(@RequestBody User user) {
        userService.toDTO(userService.createOrUpdate(user))
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    UserDTO delete(@PathVariable Long id) {
        if (!userService.exists(id))
            throw new CustomException("User with id: $id does not exist. Thus, it cannot be deleted.")
        def dto = userService.toDTO(userService.getById(id))
        userService.delete(id)
        dto
    }
}
