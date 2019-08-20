package ai.klink.yugaklink.controller

import ai.klink.yugaklink.dto.user.UserDto
import ai.klink.yugaklink.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.security.InvalidParameterException


@RestController
class UserController {

    @Autowired
    lateinit var userService: UserService

    @RequestMapping("/api/user/list")
    fun findAllUsers() : List<UserDto> {
        return userService.findAllByTenant()
    }

    @PutMapping("/api/user")
    fun addUser(@RequestBody req: UserDto): UserDto {
        userService.saveNew(req)
        return userService.findByUsername(req.username)?.
                let { UserAdapter.toDto(it) } ?:
                run { throw InvalidParameterException("Could not persist new user!") }
    }

    @DeleteMapping("api/user/{username}")
    fun disableUser(@PathVariable("username") username: String) : String {
        userService.disable(username)
        return "success"
    }

    @PutMapping("api/user/{username}/resetpassword")
    fun resetPassword(@PathVariable("username") username: String) : String {
        userService.resetPassword(username)
        return "success"
    }

}