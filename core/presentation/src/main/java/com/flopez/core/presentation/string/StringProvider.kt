package com.flopez.core.presentation.string

import android.content.Context
import androidx.annotation.StringRes

interface StringProvider {
    fun getString(@StringRes resId: Int): String
    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String
}

class AndroidStringProvider(
    private val context: Context,
) : StringProvider {
    override fun getString(resId: Int): String =
        context.getString(resId)

    override fun getString(resId: Int, vararg formatArgs: Any): String =
        context.getString(resId, *formatArgs)
}
