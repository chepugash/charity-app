package com.example.profile.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import com.example.common.base.BaseDialogFragment
import com.example.common.di.FeatureUtils
import com.example.profile.R
import com.example.profile.data.api.ProfileApi
import com.example.profile.di.ProfileFeatureComponent
import com.example.profile.presentation.ProfileViewModel

class DeleteDialogFragment : BaseDialogFragment<ProfileViewModel>() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setMessage(R.string.feature_profile_confirmation)
            .setPositiveButton(R.string.feature_profile_yes) { _, _ ->
                viewModel.deleteProfile()
            }
            .setNeutralButton(R.string.feature_profile_no) { _, _ ->
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