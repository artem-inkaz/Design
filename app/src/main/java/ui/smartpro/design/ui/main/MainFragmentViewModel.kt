package ui.smartpro.design.ui.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import ui.smartpro.design.repository.homework.HomeworkRepository
import ui.smartpro.design.repository.homework.HomeworkRepositoryImpl
import ui.smartpro.design.repository.lessons.LessonsRepository
import ui.smartpro.design.repository.lessons.LessonsRepositoryImpl
import ui.smartpro.design.base.BaseViewModel

class MainFragmentViewModel(
    app: Application,
    private val liveDataToObserve: MutableLiveData<MainFragmentAppState> =
        MutableLiveData(),
    private val lessonsRepositoryImpl: LessonsRepository = LessonsRepositoryImpl(),
    private val homeworksRepositoryImpl: HomeworkRepository = HomeworkRepositoryImpl()
) : BaseViewModel(app) {

    fun getLiveData() = liveDataToObserve

    fun getLessonsFromLocal() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        modelScope.launch {
            liveDataToObserve.value = MainFragmentAppState.Loading
//            Thread {
                Thread.sleep(700)
                liveDataToObserve.postValue(
                    MainFragmentAppState.Success(
                        lessonsRepositoryImpl.getLessons(),
                        homeworksRepositoryImpl.getHomework()
                    )
                )
//            }.start()
        }
    }


}