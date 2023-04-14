package com.example.common.base

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.common.utils.EventObserver
import javax.inject.Inject

abstract class BaseFragment<T : BaseViewModel> : Fragment() {

    @Inject
    protected open lateinit var viewModel: T

    private val observables = mutableListOf<LiveData<*>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        initViews()
        subscribe(viewModel)

        viewModel.errorLiveData.observe(EventObserver {
            showError(it)
        })

        viewModel.errorWithTitleLiveData.observe(EventObserver {
            showErrorWithTitle(it.first, it.second)
        })

        viewModel.errorFromResourceLiveData.observe(EventObserver {
            showErrorFromResponse(it)
        })
    }

    protected fun showError(errorMessage: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("")
            .setMessage(errorMessage)
            .setPositiveButton("") { _, _ -> }
            .show()
    }

    protected fun showErrorFromResponse(resId: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle("")
            .setMessage(resId)
            .setPositiveButton(android.R.string.ok) { _, _ -> }
            .show()
    }

    protected fun showErrorWithTitle(title: String, errorMessage: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(errorMessage)
            .setPositiveButton(android.R.string.ok) { _, _ -> }
            .show()
    }

    override fun onDestroyView() {
        observables.forEach { it.removeObservers(this) }
        super.onDestroyView()
    }

    protected fun <T> LiveData<T>.observe(observer: Observer<T>) {
        observe(viewLifecycleOwner, observer)
        observables.add(this)
    }

    abstract fun initViews()

    abstract fun inject()

    abstract fun subscribe(viewModel: T)
}