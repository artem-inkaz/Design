package ui.smartpro.design.repository.homework

import ui.smartpro.design.data.Homework
import ui.smartpro.design.data.getAllHomeworks

class HomeworkRepositoryImpl: HomeworkRepository {
    override fun getHomework(): List<Homework> = getAllHomeworks()




}