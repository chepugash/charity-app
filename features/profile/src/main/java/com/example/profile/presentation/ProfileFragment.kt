package com.example.profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                viewModel.onChangeName()
            }
            lChangePassword.setOnClickListener {
                viewModel.changePassword("12345678")
            }
            lDeleteProfile.setOnClickListener {
                viewModel.deleteProfile()
            }
            ivSignOut.setOnClickListener {
                viewModel.onSignOut()
            }
        }
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
                        showError("Data changed")
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
        }
    }

    private fun showUserInfo(email: String, name: String) {
        binding.run {
            tvProfileEmail.text = email
            tvProfileName.text = name
        }
    }

    private fun showError(error: Throwable) {
        activity?.findViewById<View>(android.R.id.content)
            ?.showSnackbar(error.message ?: "Error")
    }

    private fun showError(message: String) {
        activity?.findViewById<View>(android.R.id.content)
            ?.showSnackbar(message)
    }
}