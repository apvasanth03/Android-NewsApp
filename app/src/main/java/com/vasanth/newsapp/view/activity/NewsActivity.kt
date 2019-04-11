package com.vasanth.newsapp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.vasanth.newsapp.R

/**
 * Home activity for our News application.
 *
 * @author Vasanth
 */
class NewsActivity : AppCompatActivity() {

    // Properties.
    lateinit var toolbar: Toolbar

    // Activity Methods.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        toolbar = findViewById(R.id.toolbar)

        initializeViews()
    }

    // Private Methods.
    fun initializeViews() {
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}
