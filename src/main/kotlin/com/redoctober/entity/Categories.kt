package com.redoctober.entity

enum class Categories(val category: String, val id: Int) {
    HOUSING("housing", 1),
    TRAVEL("travel", 2),
    FOOD("food", 3),
    UTILITIES("utilities", 4),
    INSURANCE("insurance", 5),
    HEALTHCARE("healthcare", 6),
    FINANCIAL("financial", 7),
    LIFESTYLE("lifestyle", 8),
    ENTERTAINMEN("entertainment", 9),
    MISC("misc", 10);

    companion object {
        fun getCategoryById(id: Int): String? {
            return values().find { it.id == id }?.category
        }
    }
}