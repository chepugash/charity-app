package com.example.history.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.common.base.BaseFragment
import com.example.common.di.FeatureUtils
import com.example.common.utils.showSnackbar
import com.example.history.data.firebase.FirebaseApi
import com.example.history.databinding.FragmentHistoryBinding
import com.example.history.di.HistoryFeatureComponent
import com.example.history.presentation.adapter.SpaceItemDecorator
import com.example.history.presentation.adapter.TransactionAdapter

class HistoryFragment : BaseFragment<HistoryViewModel>() {

    private lateinit var binding: FragmentHistoryBinding

    private val adapter: TransactionAdapter by lazy(LazyThreadSafetyMode.NONE) {
        TransactionAdapter()
    }

    private val itemDecoration by lazy(LazyThreadSafetyMode.NONE) {
        SpaceItemDecorator(
            this@HistoryFragment,
            16.0f
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addDecorator(itemDecoration)
        subscribe(viewModel)

        getHistory()

        binding.toolbar.tb.setNavigationOnClickListener {
            goBack()
        }
    }

    override fun inject() {
        FeatureUtils.getFeature<HistoryFeatureComponent>(this, FirebaseApi::class.java)
            .historyComponentFactory()
            .create(this)
            .inject(this)
    }

    override fun subscribe(viewModel: HistoryViewModel) {
        with(viewModel) {
            historyList.observe(viewLifecycleOwner) { list ->
                if (list == null || list.isEmpty()) {
                    showWhenEmptyList(true)
                } else {
                    showWhenEmptyList(false)
                    adapter.submitList(list)
                    binding.rvHistory.run {
                        adapter = this@HistoryFragment.adapter
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

    private fun getHistory() {
        viewModel.getHistory()
    }

    private fun showLoading(flag: Boolean) {
        with(binding) {
            loading.isVisible = flag
            lEmpty.layoutEmpty.isVisible = !flag
            rvHistory.isVisible = !flag
        }
    }

    private fun showWhenEmptyList(flag: Boolean) {
        with(binding) {
            loading.isVisible = !flag
            lEmpty.layoutEmpty.isVisible = flag
            rvHistory.isVisible = !flag
        }
    }

    private fun addDecorator(itemDecorator: SpaceItemDecorator) {
        binding.rvHistory.addItemDecoration(itemDecorator)
    }

    private fun goBack() {
        viewModel.launchBack()
    }

    private fun showError(error: Throwable) {
        binding.root.showSnackbar(error.message ?: "Error")
    }
}