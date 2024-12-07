package com.redoctober.model

import com.redoctober.entity.TransactionEntity
import java.text.SimpleDateFormat
import java.util.*

data class TransactionBlObject(
    var paid: String,
    val id: Long,
    val summary: String,
    val category: String,
    val currency: String,
    val sum: Long
)  {
    companion object {
        fun fromEntity(transactionEntity: TransactionEntity): TransactionBlObject {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            return TransactionBlObject(
                dateFormat.format(Date(transactionEntity.paid)),
                transactionEntity.id,
                transactionEntity.summary,
                transactionEntity.category.category,
                transactionEntity.currency,
                transactionEntity.sum,
            )
        }
    }
}