
import ai.klink.yugaklink.dto.user.UserDto
import ai.klink.yugaklink.cassandra.entity.User

object UserAdapter {

    fun toDto(user: User) : UserDto {
        val userDto = UserDto()
        userDto.username = user.username ?: ""
        userDto.email = user.email ?: ""
        userDto.name = user.name
        return userDto
    }

}