package com.example.test.presentation.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.domain.model.Currency
import com.google.android.material.textview.MaterialTextView

/**
 * Adapter that creates a list of our currencies
 *
 * @property currencies list of currencies which will be added to the adapter
 * @property onSelectFavorite add [Currency] item to the MutableList<[Currency]> of the favorites
 *
 * @author Evgen K.
 */
class CurrenciesAdapter(
    val currencies: MutableList<Currency>,
    private val onSelectFavorite: (Currency) -> Unit
) :
    RecyclerView.Adapter<CurrenciesAdapter.CurrenciesViewHolder>() {

    class CurrenciesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val currency: MaterialTextView = view.findViewById(R.id.tv_currency)
        val currencyValue: MaterialTextView = view.findViewById(R.id.tv_currency_value)
        val favorites: AppCompatImageView = view.findViewById(R.id.iv_favorites)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder =
        CurrenciesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.currecy_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        with(holder) {

            val item = currencies[position]

            currency.text = item.name
            currencyValue.text = item.value

            if (item.isSelected) {
                favorites.setImageResource(R.drawable.ic_favorites_selected)
            } else {
                favorites.setImageResource(R.drawable.ic_favorites_unselected)
            }

            favorites.setOnClickListener {
                if (!item.isSelected) {
                    favorites.setImageResource(R.drawable.ic_favorites_selected)
                } else {
                    favorites.setImageResource(R.drawable.ic_favorites_unselected)
                }
                item.isSelected = !item.isSelected

                onSelectFavorite.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int = currencies.size
}