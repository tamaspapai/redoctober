package com.redoctober.controller

import com.redoctober.entity.TransactionEntity
import com.redoctober.model.TransactionBlObject
import com.redoctober.service.TransactionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/transactions")
class TransactionController(private val service: TransactionService) {
    @GetMapping
    @CrossOrigin(origins = [], allowCredentials = "false")
    fun getAll(): List<TransactionBlObject> {
        val result = service.findAll()
        return result.map { TransactionBlObject.fromEntity(it) }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<TransactionBlObject> {
        return ResponseEntity.ok(TransactionBlObject.fromEntity(service.findById(id)))
    }


    @PostMapping
    fun create(@RequestBody tr: TransactionEntity): ResponseEntity<TransactionBlObject> {
        return ResponseEntity.ok(TransactionBlObject.fromEntity(service.save(tr)))
    }


    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody tr: TransactionEntity): ResponseEntity<TransactionBlObject> {
        return ResponseEntity.ok(TransactionBlObject.fromEntity(service.update(id, tr)))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}