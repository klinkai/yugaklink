package ai.klink.yugaklink.cassandra.repository

import ai.klink.yugaklink.cassandra.entity.User
import org.springframework.data.cassandra.repository.CassandraRepository


interface UserRepository : CassandraRepository<User, String> {

    fun findByTenantAndUsername(tenant: String?, username: String?): User?

    fun findByTenant(tenant: String?): List<User>

    fun findByUsername(username: String?): User?

}