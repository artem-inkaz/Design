package ui.smartpro.design.ui.lessons

import ui.smartpro.design.data.Lessons

sealed class LessonsFragmentAppState {
    data class Success (val lessonsData: List<Lessons>
                        ): LessonsFragmentAppState()
    data class Error (val error: Throwable): LessonsFragmentAppState()
    object Loading: LessonsFragmentAppState()

}