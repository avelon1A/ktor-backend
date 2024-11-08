package service

import data.Category

class MainService {
    fun getCategories(): List<Category> {
        return listOf(
            Category("Math", 50, 100, "Completed"),
            Category("Science", 80, 120, "In Progress"),
            Category("History", 30, 50, "Not Started")
        )
    }
}