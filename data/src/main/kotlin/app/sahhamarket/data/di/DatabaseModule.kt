package app.sahhamarket.data.di

import android.content.Context
import androidx.room.Room
import app.sahhamarket.data.source.local.Constants
import app.sahhamarket.data.source.local.TemplateDataBase
import app.sahhamarket.data.source.local.dao.AddressDao
import app.sahhamarket.data.source.local.dao.CartDao
import app.sahhamarket.data.source.local.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideTemplateDatabase(@ApplicationContext context: Context): TemplateDataBase {
        return Room.databaseBuilder(
            context, TemplateDataBase::class.java, Constants.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAddressDaoTable(templateDataBase: TemplateDataBase): AddressDao {
        return templateDataBase.addressDaoTable
    }

    @Provides
    @Singleton
    fun provideCartDao(templateDataBase: TemplateDataBase): CartDao {
        return templateDataBase.cartDao
    }

    @Provides
    @Singleton
    fun provideProductDaoTable(templateDataBase: TemplateDataBase): ProductDao {
        return templateDataBase.productDaoTable
    }
}