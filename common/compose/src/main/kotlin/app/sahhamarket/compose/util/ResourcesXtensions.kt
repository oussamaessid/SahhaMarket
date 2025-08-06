package app.sahhamarket.compose.util

import android.content.res.Resources
import androidx.annotation.Dimension
import androidx.annotation.Px

@get:Px
val @receiver:Dimension(unit = Dimension.DP) Int.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

@get:Px
val @receiver:Dimension(unit = Dimension.DP) Int.dpToPxF: Float
    get() = (this * Resources.getSystem().displayMetrics.density)