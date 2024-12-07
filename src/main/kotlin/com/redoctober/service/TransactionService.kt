package com.redoctober.service

import com.redoctober.entity.TransactionEntity
import com.redoctober.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService(private val repository: TransactionRepository) {

    fun findAll(): List<TransactionEntity> = repository.findAll()

    fun findById(id: Long): TransactionEntity = repository.findById(id).orElseThrow { RuntimeException("Transaction not found!") }

    fun save(book: TransactionEntity): TransactionEntity = repository.save(book)

    fun update(id: Long, updated: TransactionEntity): TransactionEntity {
        val tr = findById(id)
        tr.summary = updated.summary
        tr.paid = updated.paid
        tr.category = updated.category
        tr.currency = updated.currency
        tr.sum = updated.sum
        return repository.save(tr)
    }

    fun delete(id: Long) = repository.deleteById(id)
}