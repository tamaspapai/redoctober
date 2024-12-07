package com.redoctober.model

import com.redoctober.entity.CategoryEnum

data class TransactionCreateObject(
    val summary: String,
    val category: CategoryEnum = CategoryEnum.MISC,
    val currency: String,
    val sum: Long
)