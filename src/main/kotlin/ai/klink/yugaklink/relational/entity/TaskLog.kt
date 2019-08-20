package ai.klink.yugaklink.relational.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="task_log")
class TaskLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var tenant: String? = null

    @ManyToOne
    @JoinColumn(name = "task_id")
    var task: Task? = null

    var workerId: String? = null

    var destination: String? = null

    var createdAt: LocalDateTime? = null

    var message: String? = null

}