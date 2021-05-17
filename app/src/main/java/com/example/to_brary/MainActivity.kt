package com.example.to_brary

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    private val apiKey = "key"
    private val applicationID = "ID"
    private var tagsList = "thereIsNoGodDamnWaySomeoneIsPuttingThisIntoTHeTextView"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //supportActionBar?.title = "Āto-Brary"
        //trying to commit to github from school computer

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Āto-Brary"
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.overflowIcon?.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP)
        //supportActionBar?.setDisplayShowTitleEnabled(false)

        //can't set onClickListener for logo

        setSupportActionBar(toolbar)

        val navController = this.findNavController(R.id.fragment_navhost_main)
        NavigationUI.setupActionBarWithNavController(this, navController)

        val actionBar = actionBar
        if(actionBar != null){
            actionBar.setDisplayShowTitleEnabled(true)
            actionBar.setDisplayShowCustomEnabled(true)
            /*var customView = layoutInflater.inflate(R.layout.actionbar_title, null)
            var customTitle = customView.findViewById<TextView>(R.id.action_bar_title)

            customTitle.setOnClickListener {
                findNavController(R.id.fragment_navhost_main).navigate(R.id.homeFragment)

            }
            actionBar.customView

            mr shorr basically said just use the right home button so not more home button on left side*/
        }
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

        val menuItem = menu!!.findItem(R.id.search)
        val searchView = menuItem.actionView as SearchView
        searchView.queryHint = "Insert Tags"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                tagsList = query
                findNavController(R.id.fragment_navhost_main).navigate(R.id.homeFragment)

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.length === 0) {
                    goToDefaultHomeFragment()
                }
                return false
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            //R.id.loginPage -> findNavController(R.id.fragment_navhost_main).navigate(R.id.loginPage)
            R.id.imagePostingPage -> findNavController(R.id.fragment_navhost_main).navigate(R.id.imagePostingPage)
            R.id.tagCreationPage -> findNavController(R.id.fragment_navhost_main).navigate(R.id.tagCreationPage)
            R.id.home -> findNavController(R.id.fragment_navhost_main).navigate(R.id.homeFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    //up button
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragment_navhost_main)
        return navController.navigateUp()
    }

    fun getItemsList(): String{
        return tagsList
    }

    fun goToDefaultHomeFragment() {
        tagsList = "thereIsNoGodDamnWaySomeoneIsPuttingThisIntoTHeTextView"
        findNavController(R.id.fragment_navhost_main).navigate(R.id.homeFragment)
    }
}



