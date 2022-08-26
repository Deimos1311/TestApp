package com.example.test.presentation.fragments.favorite_currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.FragmentFavoriteCurrenciesBinding
import com.example.test.utils.hide
import com.example.test.domain.model.Currency
import com.example.test.presentation.base.BaseFragment
import com.example.test.presentation.fragments.adapters.CurrenciesAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment that describes MutableList<[Currency]> of the favorites
 *
 * @property viewModel contains logic for this fragment
 * @property adapter contains MutableList<[Currency]> for this fragment
 * @property layoutManager layout for the [adapter]
 *
 * @author Evgen K.
 */
@AndroidEntryPoint
class FavoriteCurrenciesFragment : BaseFragment<FragmentFavoriteCurrenciesBinding>() {

    private val viewModel: FavoriteCurrenciesViewModel by viewModels()

    private lateinit var adapter: CurrenciesAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteCurrenciesBinding =
        FragmentFavoriteCurrenciesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(CURRENCY) { _, bundle ->
            binding.tvCurrency.text = bundle.getString(KEY)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                with(binding) {
                    when (state) {
                        is FavoriteCurrenciesState.Loading -> {
                            pbProgress.isVisible = state.isLoading
                        }
                        is FavoriteCurrenciesState.Success -> {
                            initAdapter(state.data)
                            pbProgress.hide()
                        }
                        is FavoriteCurrenciesState.Exception -> {
                            pbProgress.hide()
                        }
                        FavoriteCurrenciesState.Empty -> {}
                    }
                }
            }
        }
    }

    private fun initAdapter(currencies: MutableList<Currency>) {
        adapter = CurrenciesAdapter(currencies) { item ->
            viewModel.removeCurrencyFromDb(item)
        }

        layoutManager = LinearLayoutManager(requireContext())

        with(binding) {
            rvCurrenciesList.adapter = adapter
            rvCurrenciesList.layoutManager = layoutManager
        }
    }

    companion object {
        const val CURRENCY = "CURRENCY"
        const val KEY = "KEY"
    }
}