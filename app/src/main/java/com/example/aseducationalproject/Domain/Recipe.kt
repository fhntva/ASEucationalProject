package com.example.aseducationalproject.Domain

import com.example.aseducationalproject.Domain.Ingredient

class Recipe (
    val id: Int,
    val title: String,
    val ingredients: List<Ingredient>,
    val method: List<String>,
    val imageUrl: String,
)