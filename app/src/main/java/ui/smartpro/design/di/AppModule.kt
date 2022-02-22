package ui.smartpro.design.di

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ui.smartpro.design.ui.lessons.LessonsFragmentViewModel
import ui.smartpro.design.ui.main.MainFragmentViewModel

val appModule = module {
    //vm
    viewModel { MainFragmentViewModel(androidApplication()) }
    viewModel { LessonsFragmentViewModel(androidApplication()) }
}