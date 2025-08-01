package app.sahhamarket.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.sahhamarket.data.source.local.converter.ProductConverter
import app.sahhamarket.data.source.local.dao.AddressDao
import app.sahhamarket.data.source.local.dao.ProductDao
import app.sahhamarket.data.source.local.entities.AddressEntity
import app.sahhamarket.data.source.local.entities.ProductEntity

@Database(
    entities = [AddressEntity::class, ProductEntity::class],
    version = Constants.VERSION
)
@TypeConverters(
    ProductConverter::class
)
internal abstract class TemplateDataBase : RoomDatabase() {
    abstract val addressDaoTable: AddressDao
    abstract val productDaoTable: ProductDao
}