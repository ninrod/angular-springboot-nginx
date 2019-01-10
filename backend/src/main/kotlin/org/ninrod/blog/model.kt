package org.ninrod.blog

import javax.persistence.*

@Entity
data class Usuario(
    @Id val login: String,
    val firstname: String,
    val lastname: String,
    val description: String? = null
)