package ai.klink.yugaklink.relational.repository

import ai.klink.yugaklink.relational.entity.Task
import org.springframework.data.repository.CrudRepository

interface TaskRepository : CrudRepository<Task, Long>