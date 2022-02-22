package ui.smartpro.design.ui.main

import ui.smartpro.design.data.Homework
import ui.smartpro.design.data.Lessons

sealed class MainFragmentAppState {
    data class Success (val lessonsData: List<Lessons>,
                        val homeworkData: List<Homework>
                        ): MainFragmentAppState()
    data class Error (val error: Throwable): MainFragmentAppState()
    object Loading: MainFragmentAppState()

}