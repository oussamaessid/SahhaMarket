package app.sahhamarket.data.source.local.converter

import androidx.room.TypeConverter
import app.sahhamarket.domain.model.Promo
import app.sahhamarket.domain.model.Rating
import app.sahhamarket.domain.model.Status
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromPromo(promo: Promo?): String? {
        return gson.toJson(promo)
    }

    @TypeConverter
    fun toPromo(data: String?): Promo? {
        return data?.let {
            gson.fromJson(it, object : TypeToken<Promo>() {}.type)
        }
    }

    @TypeConverter
    fun fromRating(rating: Rating): String {
        return gson.toJson(rating)
    }

    @TypeConverter
    fun toRating(data: String): Rating {
        return gson.fromJson(data, object : TypeToken<Rating>() {}.type)
    }

    @TypeConverter
    fun fromStatus(status: Status): String {
        return status.name
    }

    @TypeConverter
    fun toStatus(data: String): Status {
        return Status.valueOf(data)
    }
}