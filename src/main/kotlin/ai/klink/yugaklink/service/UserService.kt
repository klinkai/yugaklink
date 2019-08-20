package ai.klink.yugaklink.service

import ai.klink.yugaklink.cassandra.entity.User
import ai.klink.yugaklink.cassandra.repository.UserRepository
import ai.klink.yugaklink.dto.user.UserDto
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

/**
 * UserService is a service is a intermediary layer between
 * controller and User
 * @author Fabio Covolo Mazzo
 */
@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    val bCryptPasswordEncoder = BCryptPasswordEncoder()

    fun init() {

    }

    /**
     * TODO: Retornar Tenant do Contexto do SpringSecurity
     */
    fun getTenantId(): String {
        return "teste"
    }


    fun resetPassword(username: String) {
        val password = RandomStringUtils.randomAlphanumeric(10)
        val varMap = mutableMapOf<String, String>()
        val user = userRepository.findByTenantAndUsername(getTenantId(), username)
        user?.let {
            /**
             * TODO: Envio de senha por email
             */
            it.password = password
            this.save(user)
        }
    }

    fun save(user: User) {
        user.password = bCryptPasswordEncoder.encode(user.password)
        userRepository.save(user)
    }

    fun findByUsername(username: String): User? {
        return userRepository.findByTenantAndUsername(getTenantId(), username)
    }

   fun findAllByTenant(): List<UserDto> {
        return userRepository.findByTenant(getTenantId()).map { UserAdapter.toDto(it) }

    }

    fun disable(username: String) {
        val user = findByUsername(username)
        user?.let {
            it.status = "INACTIVE"
            save(user)
        }
    }


    fun saveNew(userDto: UserDto) {
        val exists = userRepository.findByTenantAndUsername(getTenantId(), userDto.username) != null
        if (exists) {
            throw Exception("Email or username not available!")
        }
        val user = User()
        user.username = userDto.username
        user.status = "ACTIVE"
        user.password = userDto.password
        user.email = userDto.email
        user.name = userDto.name
        user.tenant = getTenantId()
        user.roles = userDto.roles
        save(user)
    }

}