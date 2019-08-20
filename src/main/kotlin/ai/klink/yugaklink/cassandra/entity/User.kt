package ai.klink.yugaklink.cassandra.entity

import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.Indexed
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import java.time.LocalDateTime


@Table("appuser")
class User {

    @PrimaryKeyColumn(name = "tenant", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    var tenant: String? = null

    @PrimaryKeyColumn(name = "username", ordinal = 1,type = PrimaryKeyType.CLUSTERED)
    @Indexed
    var username: String? = null

    var name = ""

    var email: String? = null

    var password: String? = null

    var randonSalt: String? = null

    var status: String? = null

    var lastLogin: LocalDateTime? = null

    var recoverPasswordToken: String? = null

    var recoverPasswordTokenDue: LocalDateTime? = null

    var attribute: Int = 0

    var roles: List<String> = mutableListOf()

}