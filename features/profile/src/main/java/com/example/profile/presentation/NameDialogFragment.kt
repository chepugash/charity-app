package com.example.profile.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.common.base.BaseDialogFragment
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.profile.data.api.ProfileApi
import com.example.profile.databinding.FragmentDialogInputBinding
import com.example.profile.di.ProfileFeatureComponent
import javax.inject.Inject

class NameDialogFragment : BaseDialogFragment<ProfileViewModel>() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = FragmentDialogInputBinding.inflate(layoutInflater)
        return AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setView(binding.root)
            .setPositiveButton("Изменить") { _, _ ->
                viewModel.changeName(binding.etName.text.toString())
            }
            .setNeutralButton("Назад") { _, _ ->
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