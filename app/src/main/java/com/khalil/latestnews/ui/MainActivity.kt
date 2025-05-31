package com.khalil.latestnews.ui

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.khalil.latestnews.R
import com.khalil.latestnews.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , View.OnClickListener{

    private var _bindig: ActivityMainBinding? = null
    private val binding get() = _bindig!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bindig = ActivityMainBinding.inflate(layoutInflater)

        //onClickMenuNavBar()
        binding.linearHome.setOnClickListener(this)
        binding.linearFavorite.setOnClickListener(this)
        binding.linearProfile.setOnClickListener(this)

        val naveHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
                as NavHostFragment
        navController = naveHostFragment.navController

        setContentView(binding.root)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.linearHome->{
                navController.popBackStack()
                //Fragment (HomeFragment)
                navController.navigate(R.id.homeFragment)
                //Icon
                binding.imgHome.setColorFilter(ContextCompat.getColor(this, R.color.primary_color), PorterDuff.Mode.SRC_IN)
                binding.imgFavorite.setColorFilter(ContextCompat.getColor(this, R.color.secoundry_color), PorterDuff.Mode.SRC_IN)
                binding.imgProfile.setColorFilter(ContextCompat.getColor(this, R.color.secoundry_color), PorterDuff.Mode.SRC_IN)
                //text
                binding.txtHome.setTextColor(ContextCompat.getColor(this, R.color.primary_color))
                binding.txtFavorite.setTextColor(ContextCompat.getColor(this, R.color.secoundry_color))
                binding.txtProfile.setTextColor(ContextCompat.getColor(this, R.color.secoundry_color))

            }
            R.id.linearFavorite->{
                navController.popBackStack()
                //Fragment (HomeFragment)
                navController.navigate(R.id.favoriteFragment)
                //Icon
                binding.imgHome.setColorFilter(ContextCompat.getColor(this, R.color.secoundry_color), PorterDuff.Mode.SRC_IN)
                binding.imgFavorite.setColorFilter(ContextCompat.getColor(this, R.color.primary_color), PorterDuff.Mode.SRC_IN)
                binding.imgProfile.setColorFilter(ContextCompat.getColor(this, R.color.secoundry_color), PorterDuff.Mode.SRC_IN)
                //text
                binding.txtHome.setTextColor(ContextCompat.getColor(this, R.color.secoundry_color))
                binding.txtFavorite.setTextColor(ContextCompat.getColor(this, R.color.primary_color))
                binding.txtProfile.setTextColor(ContextCompat.getColor(this, R.color.secoundry_color))

            }
            R.id.linearProfile->{
                navController.popBackStack()
                //Fragment (HomeFragment)
                navController.navigate(R.id.profileFragment)
                //Icon
                binding.imgHome.setColorFilter(ContextCompat.getColor(this, R.color.secoundry_color), PorterDuff.Mode.SRC_IN)
                binding.imgFavorite.setColorFilter(ContextCompat.getColor(this, R.color.secoundry_color), PorterDuff.Mode.SRC_IN)
                binding.imgProfile.setColorFilter(ContextCompat.getColor(this, R.color.primary_color), PorterDuff.Mode.SRC_IN)
                //text
                binding.txtHome.setTextColor(ContextCompat.getColor(this, R.color.secoundry_color))
                binding.txtFavorite.setTextColor(ContextCompat.getColor(this, R.color.secoundry_color))
                binding.txtProfile.setTextColor(ContextCompat.getColor(this, R.color.primary_color))

            }
            else ->{}
        }

    }
}