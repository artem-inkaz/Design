package ui.smartpro.design.ui.main

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ui.smartpro.design.R
import ui.smartpro.design.base.BaseFragment
import ui.smartpro.design.data.Homework
import ui.smartpro.design.data.Lessons
import ui.smartpro.design.databinding.FragmentMainBinding
import ui.smartpro.design.repository.OnItemViewClickListener
import ui.smartpro.design.ui.lessons.MainLessonsAdapter
import java.text.SimpleDateFormat
import java.util.*

class FragmentMain(
    override val layoutId: Int = R.layout.fragment_main
) :
    BaseFragment<FragmentMainBinding>(), OnItemViewClickListener {
    private val mainLessonsViewModel by viewModel<MainFragmentViewModel>()
    private lateinit var allLessons: RecyclerView
    private lateinit var allHomeworks: RecyclerView
    private lateinit var mainLessonsAdapter: MainLessonsAdapter
    private lateinit var homeworkAdapter: HomeworkAdapter
    override fun initViews() {
        super.initViews()
        mainLessonsViewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        mainLessonsViewModel.getLessonsFromLocal()
    }

    private fun renderData(appState: MainFragmentAppState) {
        when (appState) {
            is MainFragmentAppState.Success -> {
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                binding.lessonsRv.visibility = View.VISIBLE
                binding.homeworksRv.visibility = View.VISIBLE
                binding.lessonsTextview.visibility = View.VISIBLE
                binding.homeworkTextview.visibility = View.VISIBLE
                setLessonsData(appState.lessonsData, appState.homeworkData)
            }
            is MainFragmentAppState.Loading -> {
                binding.includedLoadingLayout.loadingLayout.visibility = View.VISIBLE
                binding.lessonsRv.visibility = View.GONE
                binding.homeworksRv.visibility = View.GONE
                binding.lessonsTextview.visibility = View.GONE
                binding.homeworkTextview.visibility = View.GONE
            }
            is MainFragmentAppState.Error -> {
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                binding.lessonsRv.visibility = View.VISIBLE
                binding.homeworksRv.visibility = View.VISIBLE
                binding.lessonsTextview.visibility = View.VISIBLE
                binding.homeworkTextview.visibility = View.VISIBLE
            }
        }
    }

    private fun setLessonsData(lessons: List<Lessons>, homework: List<Homework>) {
        allLessons = binding.lessonsRv
        allLessons.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        mainLessonsAdapter = MainLessonsAdapter(this)
        allLessons.adapter = mainLessonsAdapter
        mainLessonsAdapter.setLessons(lessons)
        if (lessons.isNotEmpty()) {
            binding.countLessonsTextview.text = String.format(
                resources.getString(R.string.count_lessons_today),
                lessons.size
            )
        } else binding.countLessonsTextview.text = resources.getString(R.string.dont_lessons_today)

        for (doc in lessons) {
            if (doc.time.toDouble() <= getCurrentTime().toDouble()) {
                allLessons.scrollToPosition(doc.id)
            }
            /** Проверка
            if (doc.time.toDouble() <= 11.35){
            allLessons.scrollToPosition(doc.id)
            }
             */
        }

        allHomeworks = binding.homeworksRv
        allHomeworks.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        homeworkAdapter = HomeworkAdapter()
        allHomeworks.adapter = homeworkAdapter
        homeworkAdapter.setHomeworks(homework)

    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentTime(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("HH.mm")
        return dateFormat.format(calendar.time)
    }

    override fun onItemViewClick(lessons: Lessons) {
        initiateSkypeUri(requireContext(), ("skype:Artem8423"))
//        skypeCall()
    }

    private fun skypeCall() {
        val skypeVideo = Intent("android.intent.action.VIEW")
        skypeVideo.data = Uri.parse("skype:" + "Artem8423" + "?call&video=true")
        startActivity(skypeVideo)
    }

    /**
     * Initiate the actions encoded in the specified URI.
     */
    fun initiateSkypeUri(myContext: Context, mySkypeUri: String?) {

        // Make sure the Skype for Android client is installed.
        if (!isSkypeClientInstalled(myContext)) {
            goToMarket(myContext)
            return
        }

        // Create the Intent from our Skype URI.
        val skypeUri = Uri.parse(mySkypeUri)
        val myIntent = Intent(Intent.ACTION_VIEW, skypeUri)

        // Restrict the Intent to being handled by the Skype for Android client only.
        myIntent.component = ComponentName("com.skype.raider", "com.skype.raider.Main")
        myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        // Initiate the Intent. It should never fail because you've already established the
        // presence of its handler (although there is an extremely minute window where that
        // handler can go away).
        myContext.startActivity(myIntent)
        return
    }

    /**
     * Determine whether the Skype for Android client is installed on this device.
     */
    private fun isSkypeClientInstalled(myContext: Context): Boolean {
        val myPackageMgr: PackageManager = myContext.getPackageManager()
        try {
            myPackageMgr.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES)
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
        return true
    }

    /**
     * Install the Skype client through the market: URI scheme.
     */
    fun goToMarket(myContext: Context) {
        val marketUri = Uri.parse("market://details?id=com.skype.raider")
        val myIntent = Intent(Intent.ACTION_VIEW, marketUri)
        myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        myContext.startActivity(myIntent)
        return
    }
}