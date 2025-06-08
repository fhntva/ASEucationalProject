package com.example.aseducationalproject.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Recipe (
    val id: Int,
    val title: String,
    val ingredients: List<Ingredient>,
    val method: List<String>,
    val imageUrl: String,
) : Parcelable