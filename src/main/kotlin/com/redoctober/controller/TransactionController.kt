package com.redoctober.controller

import com.redoctober.entity.TransactionEntity
import com.redoctober.service.TransactionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/transactions")
class TransactionController(private val service: TransactionService) {
    @GetMapping
    fun getAll(): List<TransactionEntity> = service.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<TransactionEntity> =
        ResponseEntity.ok(service.findById(id))

    @PostMapping
    fun create(@RequestBody tr: TransactionEntity): ResponseEntity<TransactionEntity> =
        ResponseEntity.ok(service.save(tr))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody tr: TransactionEntity): ResponseEntity<TransactionEntity> =
        ResponseEntity.ok(service.update(id, tr))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}