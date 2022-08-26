package com.example.test.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Abstract class base on which fragments are crated
 *
 * @property ViewBindingType viewBinding for every fragment
 * @property [_binding]/[binding] binds fragment to the layout
 *
 * @author Evgen K.
 */
abstract class BaseFragment<ViewBindingType : ViewBinding> : Fragment() {

    private var _binding: ViewBindingType? = null
    open val binding get() = requireNotNull(_binding)

    abstract fun setupViewBinding(
        inflater: LayoutInflater, container: ViewGroup?,
    ): ViewBindingType

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = setupViewBinding(inflater, container)
        return requireNotNull(_binding).root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}