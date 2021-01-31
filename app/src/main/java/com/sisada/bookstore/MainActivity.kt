package com.sisada.bookstore

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView

import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sisada.bookstore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        setSupportActionBar(binding.toolbar)
        val actionBar : ActionBar? = supportActionBar
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        val pageAdapter = MyPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = pageAdapter

        //https://stackoverflow.com/questions/55372259/how-to-use-tablayout-with-viewpager2-in-android
        TabLayoutMediator(binding.tabLayout, binding.viewPager)
        { tab, position -> // Styling each tab here
            when(position){
                0 -> {tab.text = "ONE"}
                1 -> {tab.text = "TWO"}
                2 -> {tab.text = "THREE"}
                else -> {tab.text = "$position"}
            }

        }.attach()

//        drawerLayout = findViewById(R.id.drawer_layout)
//        setSupportActionBar(findViewById(R.id.toolbar))
//
//        val actionBar : ActionBar? = supportActionBar
//
//        actionBar?.apply {
//            setDisplayHomeAsUpEnabled(true)
//            setHomeAsUpIndicator(R.drawable.ic_menu)
//        }
//
        val navigationView : NavigationView = findViewById(R.id.navigation_view)

        navigationView.setNavigationItemSelectedListener {

            menuItem ->
            when(menuItem.itemId){
                R.id.profile ->{
                    val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                    startActivity(intent)
                }
                R.id.about ->{
                    val intent = Intent(this@MainActivity, AboutActivity::class.java)
                    startActivity(intent)
                }
            }

            binding.drawerLayout.closeDrawers()
            true

        }
//
//        viewPager = findViewById(R.id.view_pager)
//        tabLayout = findViewById(R.id.tab_layout)
//
//        val pagerAdapter = MyPagerAdapter(supportFragmentManager)
//
//        viewPager.adapter = pagerAdapter
//
//        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item?.itemId){
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)

        }

    }

}
