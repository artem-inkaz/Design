package ui.smartpro.design.repository.lessons

import ui.smartpro.design.data.Lessons
import ui.smartpro.design.data.getAllLessons

class LessonsRepositoryImpl: LessonsRepository {
    override fun getLessons(): List<Lessons> = getAllLessons()

}