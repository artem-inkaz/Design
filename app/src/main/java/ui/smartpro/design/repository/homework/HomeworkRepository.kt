package ui.smartpro.design.repository.homework

import ui.smartpro.design.data.Homework

interface HomeworkRepository {
    fun getHomework(): List<Homework>
}