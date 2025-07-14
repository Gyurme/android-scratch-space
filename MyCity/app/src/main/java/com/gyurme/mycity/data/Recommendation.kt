package com.gyurme.mycity.data

import androidx.annotation.StringRes

data class Recommendation(
    val id: Long,
    val category: Category,
    @StringRes val title: Int
)
