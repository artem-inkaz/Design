package ui.smartpro.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import ui.smartpro.design.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mToolbar: Toolbar
    lateinit var binding: ActivityMainBinding
    private lateinit var bottom_navigation: ChipNavigationBar
    private lateinit var bottomCompanyNavigation: ChipNavigationBar
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mToolbar = binding.toolbar
        bottom_navigation = binding.menu
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_main_fragment) as NavHostFragment
        navController = navHostFragment.navController

        initBottomNavigation()
        menuPlaceClick()
    }

    private fun initBottomNavigation() {

        bottom_navigation.setOnItemSelectedListener {
            when (it) {
                R.id.app_home -> {
                    mToolbar.menu.clear()
                    mToolbar.inflateMenu(R.menu.toolbar)
                    navController.navigate(R.id.fragmentMain)
                    mToolbar.title = resources.getString(R.string.greeting)
                    true
                }
                R.id.app_lessons -> {
                    mToolbar.menu.clear()
                    mToolbar.inflateMenu(R.menu.toolbar_v)
                    mToolbar.title = resources.getString(R.string.lessons_today)
                    navController.navigate(R.id.fragmentLessons)
                    true
                }
                else -> true
            }
        }
    }

    private fun menuPlaceClick() {
        mToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_search -> {}
                R.id.action_schedule -> {}
                R.id.action_profile -> {}
            }
            true
        }

    }
}