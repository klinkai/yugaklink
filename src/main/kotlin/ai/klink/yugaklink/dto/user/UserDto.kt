package ai.klink.yugaklink.dto.user

/**
 * UserDto to represents a User in view.
 * @author Fabio Covolo Mazzo
 */
class UserDto {

    var username = ""

    var name = ""

    var email = ""

    var password = ""

    var roles = mutableListOf<String>()

    var tenant = ""

}