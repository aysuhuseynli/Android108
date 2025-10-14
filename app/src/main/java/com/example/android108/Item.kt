package com.example.android108

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val title: String = "",
    val desc: String = "",
    val image: Int = 0,
    var isSelected: Boolean = false
): Parcelable
