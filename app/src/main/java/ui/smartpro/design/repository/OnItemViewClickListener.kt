package ui.smartpro.design.repository

import ui.smartpro.design.data.Lessons

interface OnItemViewClickListener {
    fun onItemViewClick(lessons: Lessons)
}