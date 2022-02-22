package ui.smartpro.design.ui.lessons

import android.app.Application
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import ui.smartpro.design.repository.lessons.LessonsRepository
import ui.smartpro.design.repository.lessons.LessonsRepositoryImpl
import ui.smartpro.design.base.BaseViewModel
import java.lang.Thread.sleep

class LessonsFragmentViewModel(
    app: Application,
    private val liveDataToObserve: MutableLiveData<LessonsFragmentAppState> =
        MutableLiveData(),
    private val lessonsRepositoryImpl: LessonsRepository = LessonsRepositoryImpl()
) : BaseViewModel(app) {

    fun getLiveData() = liveDataToObserve

    fun getLessonsFromLocal() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        modelScope.launch {
            liveDataToObserve.value = LessonsFragmentAppState.Loading
//            Thread {
                sleep(700)
                liveDataToObserve.postValue(
                    LessonsFragmentAppState.Success(
                        lessonsRepositoryImpl.getLessons()
                    )
                )
//            }.start()
        }
    }


}