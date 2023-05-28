package com.example.profile.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.common.utils.showSnackbar
import com.example.profile.data.api.ProfileApi
import com.example.profile.databinding.FragmentProfileBinding
import com.example.profile.di.ProfileFeatureComponent
import com.example.profile.domain.entity.ApiResult

class ProfileFragment : BaseFragment<ProfileViewModel>() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe(viewModel)

        binding.run {
            lName.setOnClickListener {
                onChangeName()
            }
            lChangePassword.setOnClickListener {
                onChangePassword()
            }
            lDeleteProfile.setOnClickListener {
                onDelete()
            }
            ivSignOut.setOnClickListener {
                onSignOut()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    override fun inject() {
        FeatureUtils.getFeature<ProfileFeatureComponent>(this, ProfileApi::class.java)
            .profileComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: ProfileViewModel) {
        with(viewModel) {
            user.observe(viewLifecycleOwner) {
                if (it == null) return@observe
                showUserInfo(it.email, it.name)
            }
            apiResult.observe(viewLifecycleOwner) {
                when (it) {
                    is ApiResult.Success -> {
                        viewModel.getUser()
                    }
                    is ApiResult.Error -> {
                        showError(it.message)
                    }
                }
            }
            error.observe(viewLifecycleOwner) {
                if (it == null) return@observe
                showError(it)
            }
            loading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
        }
    }

    private fun showUserInfo(email: String, name: String) {
        binding.run {
            tvProfileEmail.text = email
            tvProfileName.text = name
        }
    }

    private fun onChangeName() {
        viewModel.onChangeName()
    }

    private fun onChangePassword() {
        viewModel.onChangePassword()
    }

    private fun onDelete() {
        viewModel.onDelete()
    }

    private fun onSignOut() {
        viewModel.onSignOut()
    }

    private fun showError(error: Throwable) {
        binding.root.showSnackbar(error.message ?: "Error")
    }

    private fun showError(message: String) {
        binding.root.showSnackbar(message)
    }

    private fun showLoading(flag: Boolean) {
        with(binding) {
            if (flag) {
                loading.isVisible = true
                content.isVisible = false
            } else {
                loading.isVisible = false
                content.isVisible = true
            }
        }
    }
}