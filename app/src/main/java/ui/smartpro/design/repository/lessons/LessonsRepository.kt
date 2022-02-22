package ui.smartpro.design.repository.lessons

import ui.smartpro.design.data.Lessons

interface LessonsRepository {

    fun getLessons(): List<Lessons>
}