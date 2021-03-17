package com.example.to_brary

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.backendless.Backendless
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val apiKey = "key"
    private val applicationID = "ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = this.findNavController(R.id.fragment_navhost_main)
        NavigationUI.setupActionBarWithNavController(this,navController)

        Backendless.initApp(this, applicationID,apiKey)

    }

    //options menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        if (menu != null) {
            (menu.findItem(R.id.search).actionView as SearchView).apply {
                setSearchableInfo(searchManager.getSearchableInfo(componentName))
            }
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.loginPage -> findNavController(R.id.fragment_navhost_main).navigate(R.id.loginPage)
            R.id.imagePostingPage -> findNavController(R.id.fragment_navhost_main).navigate(R.id.imagePostingPage)
            R.id.tagCreationPage -> findNavController(R.id.fragment_navhost_main).navigate(R.id.tagCreationPage)
        }
        return super.onOptionsItemSelected(item)
    }

    //up button
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragment_navhost_main)
        return navController.navigateUp()
    }
}