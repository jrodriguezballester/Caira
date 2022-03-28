package com.example.caira15.activities.principal

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.caira15.R
import com.example.caira15.activities.main.MainActivity
import com.example.caira15.databinding.ActivityBodyappBinding
import com.example.caira15.viewmodels.BodyViewModel
import com.google.android.material.navigation.NavigationView


class BodyappActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityBodyappBinding
    private lateinit var viewModel : BodyViewModel
    //  var listaActiveProgram= ListProgramFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBodyappBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarBodyapp.toolbar)
        // viewModel
        viewModel = ViewModelProvider(this).get(BodyViewModel::class.java)
        viewModel.status.observe(this, Observer { status -> status?.let {
            viewModel.status.value=null
            Toast.makeText(this, "session close", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        } })

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_bodyapp)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_slideshow,
                R.id.opportunitiesFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        public abstract MenuItem add (
//        int groupId,
//        int itemId,
//        int order,
//        int titleRes)

        menu.add(
            0,
            1,
            1,
            menuIconWithText(
                getResources().getDrawable(R.drawable.ic_settings),
                getResources().getString(R.string.account_settings)
            )
        );
        menu.add(
            0,
            2,
            2,
            menuIconWithText(
                getResources().getDrawable(R.drawable.ic_social),
                getResources().getString(R.string.social_profile)
            )
        );
        menu.add(
            0,
            3,
            3,
            menuIconWithText(
                getResources().getDrawable(R.drawable.ic_logout),
                getResources().getString(R.string.logout)
            )
        );
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.bodyapp, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action4 -> {
                action(1)
                true
            }
            2 -> {
              // viewModel.logout()

                true
            }
            3 -> {
                viewModel.logout()
                true
            }
            4 -> {
                action(4)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun action(int: Int) {
        Toast.makeText(this, int.toString(), Toast.LENGTH_SHORT).show()
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_bodyapp)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun menuIconWithText(r: Drawable, title: String): CharSequence? {
        r.setBounds(0, 0, r.intrinsicWidth, r.intrinsicHeight)
        val sb = SpannableString("    $title")
        val imageSpan = ImageSpan(r, ImageSpan.ALIGN_BOTTOM)
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return sb
    }
}