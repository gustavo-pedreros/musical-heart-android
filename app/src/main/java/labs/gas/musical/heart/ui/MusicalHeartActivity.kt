package labs.gas.musical.heart.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import labs.gas.musical.heart.R
import labs.gas.musical.heart.databinding.ActivityMusicalHeartBinding
import javax.inject.Inject

class MusicalHeartActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>
    private lateinit var binding: ActivityMusicalHeartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityMusicalHeartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_media, R.id.navigation_favorites)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun androidInjector(): AndroidInjector<Any> = injector
}
