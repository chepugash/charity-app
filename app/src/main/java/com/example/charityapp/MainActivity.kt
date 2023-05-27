package com.example.charityapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.charityapp.databinding.ActivityMainBinding
import com.example.charityapp.di.deps.findComponentDependencies
import com.example.charityapp.di.main.MainComponent
import com.example.charityapp.navigation.Navigator
import com.example.common.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

        val noBottomBarFragments = listOf<Int>(
            R.id.signInFragment,
            R.id.signUpFragment,
            R.id.foundationFragment
        )
    }

    @Inject lateinit var navigator: Navigator

    private var binding: ActivityMainBinding? = null

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        inject()
        initViews()
        subscribe(viewModel)
    }

    override fun inject() {
        MainComponent.init(this, findComponentDependencies())
            .inject(this)
    }

    override fun layoutResource(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {
        navController = Navigation.findNavController(this, R.id.container)
        navigator.attachNavController(navController!!, R.navigation.nav_graph)

        binding?.run {
            bnvMain.setupWithNavController(navController!!)

            navController?.addOnDestinationChangedListener { _, destination, _ ->
                if (noBottomBarFragments.contains(destination.id)) {
                    bnvMain.visibility = View.GONE
                } else {
                    bnvMain.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun subscribe(viewModel: MainViewModel) {
    }

    override fun onDestroy() {
        super.onDestroy()
        navController?.let {
            navigator.detachNavController(it)
        }
    }
}
