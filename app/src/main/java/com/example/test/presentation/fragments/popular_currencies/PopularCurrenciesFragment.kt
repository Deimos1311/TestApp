package com.example.test.presentation.fragments.popular_currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.databinding.FragmentPopularCurrenciesBinding
import com.example.test.utils.hide
import com.example.test.domain.model.Currency
import com.example.test.presentation.base.BaseFragment
import com.example.test.presentation.fragments.adapters.CurrenciesAdapter
import com.example.test.presentation.fragments.favorite_currencies.FavoriteCurrenciesFragment.Companion.CURRENCY
import com.example.test.presentation.fragments.favorite_currencies.FavoriteCurrenciesFragment.Companion.KEY
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment that describes MutableList<[Currency]> of all currencies
 *
 * @property viewModel contains logic for this fragment
 * @property adapter contains MutableList<[Currency]> for this fragment
 * @property layoutManager layout for the [adapter]
 *
 * @author Evgen K.
 */
@AndroidEntryPoint
class PopularCurrenciesFragment : BaseFragment<FragmentPopularCurrenciesBinding>() {

    private val viewModel: PopularCurrenciesViewModel by viewModels()

    private lateinit var adapter: CurrenciesAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPopularCurrenciesBinding =
        FragmentPopularCurrenciesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClick()

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                with(binding) {
                    when (state) {
                        is PopularCurrenciesState.Loading -> {
                            pbProgress.isVisible = state.isLoading
                        }
                        is PopularCurrenciesState.Success -> {
                            initAdapter(state.data)
                            pbProgress.hide()
                        }
                        is PopularCurrenciesState.Exception -> {
                            Toast.makeText(
                                requireContext(),
                                "Exception -- ${state.message}",
                                Snackbar.LENGTH_SHORT
                            ).show()
                            pbProgress.hide()
                        }
                        PopularCurrenciesState.Empty -> Unit
                    }
                }
            }
        }
    }

    private fun initClick() {
        initSpinner { sortingType ->
            with(binding) {
                btnSearch.setOnClickListener {
                    val currency = etChooseCurrency.text.toString()

                    setFragmentResult(CURRENCY, bundleOf(KEY to currency))
                    viewModel.getAllCurrencies(currency, sortingType)
                }
            }
        }
    }

    private fun initSpinner(sortingType: (String) -> Unit) {
        val listener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                sortingType.invoke(parent.getItemAtPosition(position).toString())
                binding.btnSearch.performClick()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner,
            android.R.layout.simple_spinner_dropdown_item
        )

        val spinner = binding.spnSort
        spinner.onItemSelectedListener = listener
        spinner.adapter = spinnerAdapter
    }

    private fun initAdapter(currencies: MutableList<Currency>) {
        adapter = CurrenciesAdapter(currencies) { item ->
            if (item.isSelected) {
                viewModel.addCurrencyToDb(item)
            } else {
                viewModel.removeCurrencyFromDb(item)
            }
        }

        layoutManager = LinearLayoutManager(requireContext())
        binding.rvCurrenciesList.layoutManager = layoutManager
        binding.rvCurrenciesList.adapter = adapter
    }
}