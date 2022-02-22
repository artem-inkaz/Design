package ui.smartpro.design.ui.lessons

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ui.smartpro.design.R
import ui.smartpro.design.data.Lessons
import ui.smartpro.design.databinding.FragmentLessonsBinding
import ui.smartpro.design.repository.OnItemViewClickListener
import ui.smartpro.design.base.BaseFragment

class FragmentLessons(
    override val layoutId: Int = R.layout.fragment_lessons
)  :
    BaseFragment<FragmentLessonsBinding>(), OnItemViewClickListener {
    private val lessonsViewModel by viewModel<LessonsFragmentViewModel>()
    override fun initViews() {
        super.initViews()
    }

    override fun initViewModel() {
        super.initViewModel()
        lessonsViewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        lessonsViewModel.getLessonsFromLocal()
    }

    private fun renderData(appState: LessonsFragmentAppState) {
        when (appState) {
            is LessonsFragmentAppState.Success -> {
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                binding.lessonsRv.visibility = View.VISIBLE

                setLessonsData(appState.lessonsData)
            }
            is LessonsFragmentAppState.Loading -> {
                binding.includedLoadingLayout.loadingLayout.visibility = View.VISIBLE
                binding.lessonsRv.visibility = View.GONE

            }
            is LessonsFragmentAppState.Error -> {
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                binding.lessonsRv.visibility = View.VISIBLE
            }
        }
    }

    private fun setLessonsData(lessons: List<Lessons>) {
        val allLessons: RecyclerView = binding.lessonsRv
        allLessons.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        val lessonsRecyclerAdapter = MainLessonsAdapter(object : OnItemViewClickListener {
            override fun onItemViewClick(lessons: Lessons) {
                val intent: Intent? =
                    activity?.packageManager?.getLaunchIntentForPackage("com.whatsapp")
                if (intent != null) {
                    startActivity(intent)
                }
            }
        })
        allLessons.adapter = lessonsRecyclerAdapter
        lessonsRecyclerAdapter.setLessons(lessons)

    }

    override fun onItemViewClick(lessons: Lessons) {
        TODO("Not yet implemented")
    }
}