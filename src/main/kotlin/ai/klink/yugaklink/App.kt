package ai.klink.yugaklink

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling
import java.util.*
import javax.annotation.PostConstruct


@Configuration
@SpringBootApplication(scanBasePackages = ["ai.klink.yugaklink"])
@EntityScan(basePackages = ["ai.klink.yugaklink.cassandra", "ai.klink.yugaklink.relational"])
@EnableCassandraRepositories(basePackages = ["ai.klink.yugaklink.cassandra"])
@EnableJpaRepositories(basePackages = ["ai.klink.yugaklink.relational"])
@EnableScheduling
class YugaKlinkApplication {

    @PostConstruct
    fun init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(YugaKlinkApplication::class.java, *args)
}
