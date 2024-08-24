package com.example.ktspringrestfulapi

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class UserService(val db: JdbcTemplate) {

    fun findById(id: String): User? {
        return db.queryForObject(
                "SELECT * FROM users WHERE id = CAST(? AS UUID)",
                arrayOf(id)
        ) { rs, _ ->
            User(
                id = rs.getObject("id", UUID::class.java).toString(),
                username = rs.getString("username"),
                password = rs.getString("password"),
                createdAt = rs.getObject("created_at", LocalDateTime::class.java),
                updatedAt = rs.getObject("updated_at", LocalDateTime::class.java)
            )
        }
    }

    fun findAll(): List<User> {
        return db.query(
            "SELECT * FROM users"
        ) { rs, _ ->
            User(
                id = rs.getObject("id", UUID::class.java).toString(),
                username = rs.getString("username"),
                password = rs.getString("password"),
                createdAt = rs.getObject("created_at", LocalDateTime::class.java),
                updatedAt = rs.getObject("updated_at", LocalDateTime::class.java)
            )
        }
    }

    fun save(user: User) {
        val id = user.id ?: UUID.randomUUID()
        db.update(
            "INSERT INTO users (id, username, password, created_at, updated_at) VALUES (CAST(? AS UUID), ?, ?, ?, ?)",
            id, user.username, user.password, user.createdAt, user.updatedAt
        )
    }

    fun update(user: User) {
        db.update(
            "UPDATE users SET username = ?, password = ?, updated_at = ? WHERE id = CAST(? AS UUID)",
            user.username, user.password, user.updatedAt, user.id
        )
    }

    fun remove(id: String) {
        db.update("DELETE FROM users WHERE id = CAST(? AS UUID)", id)
    }
}
