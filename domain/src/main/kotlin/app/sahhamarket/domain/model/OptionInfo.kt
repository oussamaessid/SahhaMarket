package app.sahhamarket.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class OptionInfo(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
)