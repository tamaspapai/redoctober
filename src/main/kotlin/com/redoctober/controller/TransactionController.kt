package com.redoctober.controller

import com.redoctober.entity.CategoryEnum
import com.redoctober.entity.TransactionEntity
import com.redoctober.model.TransactionBlObject
import com.redoctober.model.TransactionCreateObject
import com.redoctober.service.TransactionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.time.LocalDate
import java.time.YearMonth


@RestController
@RequestMapping("/api/transactions")
class TransactionController(private val service: TransactionService) {
    @GetMapping
    @CrossOrigin(origins = [], allowCredentials = "false")
    fun getAll(): List<TransactionBlObject> {
        val transactions = service.findAll()
        return transactions.map { TransactionBlObject.fromEntity(it) }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<TransactionBlObject> {
        return ResponseEntity.ok(TransactionBlObject.fromEntity(service.findById(id)))
    }


    @PostMapping
    fun create(@RequestBody tr: TransactionCreateObject): ResponseEntity<TransactionBlObject> {
        val entity = TransactionEntity(null, tr.summary, tr.category, tr.currency, tr.sum, Instant.now().toEpochMilli())
        return ResponseEntity.ok(TransactionBlObject.fromEntity(service.save(entity)))
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

    @GetMapping("/forecast")
    fun forecastByMonth(): Map<CategoryEnum, Double> {
        val transactions = service.findAll()

        val currentDate = LocalDate.now()

        val yearMonth = YearMonth.of(currentDate.year, currentDate.monthValue)

        val daysInMonth = yearMonth.lengthOfMonth()
        val multiplier = daysInMonth.toDouble() / currentDate.dayOfMonth.toDouble()

        val summary = transactions.groupBy { it.category }
            .mapValues { (_, values) -> values.sumOf { it.sum.toDouble() * multiplier } }

        return summary
    }
}