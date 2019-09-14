package com.project.framework.core

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.project.framework.BR
import com.project.framework.core.owner.ViewDataBindingOwner
import com.project.framework.core.owner.ViewModelOwner


abstract class BaseViewHolder<T>(val context: Context, view: View) : RecyclerView.ViewHolder(view) {

    init {
        if (this is ViewDataBindingOwner<*>) {
            setViewBinding(view)
            if (this is ViewModelOwner<*>) {
                binding.setVariable(BR.vm, viewModel)
                binding.executePendingBindings()
            }
        }
    }

    abstract fun bindData(data: T)
}