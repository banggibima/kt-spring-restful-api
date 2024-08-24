package com.example.ktspringrestfulapi

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class User(
    @JsonProperty("id")
    val id: String? = null,

    @JsonProperty("username")
    var username: String,

    @JsonProperty("password")
    var password: String,

    @JsonProperty("created_at")
    var createdAt: LocalDateTime? = null,

    @JsonProperty("updated_at")
    var updatedAt: LocalDateTime? = null
)
