package com.redoctober.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.time.Instant
import java.time.LocalDate

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
data class TransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var summary: String = "",

    var category: Categories = Categories.MISC,

    var currency: String = "HUF",

    var sum: Long = 0,

    var paid: Long = Instant.now().toEpochMilli()
)