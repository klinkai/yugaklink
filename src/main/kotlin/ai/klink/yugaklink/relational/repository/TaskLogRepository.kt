package ai.klink.yugaklink.relational.repository

import ai.klink.yugaklink.relational.entity.TaskLog
import org.springframework.data.repository.CrudRepository

interface TaskLogRepository : CrudRepository<TaskLog, Long>