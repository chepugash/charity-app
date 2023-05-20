package com.example.profile.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import com.example.common.base.BaseDialogFragment
import com.example.common.di.FeatureUtils
import com.example.profile.R
import com.example.profile.data.api.ProfileApi
import com.example.profile.databinding.FragmentDialogNameBinding
import com.example.profile.di.ProfileFeatureComponent
import com.example.profile.presentation.ProfileViewModel

class NameDialogFragment : BaseDialogFragment<ProfileViewModel>() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = FragmentDialogNameBinding.inflate(layoutInflater)

        return AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setView(binding.root)
            .setTitle(R.string.feature_profile_change_name)
            .setPositiveButton(R.string.feature_profile_change) { _, _ ->
                viewModel.changeName(binding.etName.text.toString())
            }
            .setNeutralButton(R.string.feature_profile_back) { _, _ ->
                dismiss()
            }
            .create()
    }

    override fun inject() {
        FeatureUtils.getFeature<ProfileFeatureComponent>(this, ProfileApi::class.java)
            .profileComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: ProfileViewModel) {

    }

}