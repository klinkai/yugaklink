package ai.klink.yugaklink.relational.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="task")
class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var tenant: String? = null

    var scheduledTime: LocalDateTime? = null

    var destination: String? = null

    var designedWorker: String? = null

    var payload: String? = null

    var startTime: LocalDateTime? = null

    @OneToMany(mappedBy = "task")
    var taskLogs: MutableList<TaskLog>? = null

    // Estimated Time of Completion
    var etcTime: LocalDateTime? = null

    var endTime: LocalDateTime? = null

    var responsePayload: String? = null

    var workerId: String? = null

    var success: Boolean? = null

    var responseMessage: String? = null

}