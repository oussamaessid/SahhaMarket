package com.example.sahhamarket.di

import app.sahhamarket.data.repository.AuthRepositoryImpl
import app.sahhamarket.data.repository.CartRepositoryImpl
import app.sahhamarket.data.repository.CategoriesRepositoryImpl
import app.sahhamarket.data.repository.LocationRepositoryImpl
import app.sahhamarket.data.repository.PackageRepositoryImpl
import app.sahhamarket.data.repository.PopularDealsRepositoryImpl
import app.sahhamarket.data.repository.ProductsRepositoryImpl
import app.sahhamarket.data.repository.PromoCardsRepositoryImpl
import app.sahhamarket.data.repository.RecipesRepositoryImpl
import app.sahhamarket.data.repository.SubCategoriesRepositoryImpl
import app.sahhamarket.domain.repository.AuthRepository
import app.sahhamarket.domain.repository.CartRepository
import app.sahhamarket.domain.repository.CategoriesRepository
import app.sahhamarket.domain.repository.LocationRepository
import app.sahhamarket.domain.repository.PackageRepository
import app.sahhamarket.domain.repository.PopularDealsRepository
import app.sahhamarket.domain.repository.ProductsRepository
import app.sahhamarket.domain.repository.PromoCardsRepository
import app.sahhamarket.domain.repository.RecipesRepository
import app.sahhamarket.domain.repository.SubCategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface SahhaMarketModule {

    @Binds
    @Singleton
    fun provideLocationRepository(locationRepositoryImpl: LocationRepositoryImpl): LocationRepository

    @Binds
    @Singleton
    fun provideProductsRepository(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository

    @Binds
    @Singleton
    fun provideCategoriesRepository(categoriesRepositoryImpl: CategoriesRepositoryImpl): CategoriesRepository

    @Binds
    @Singleton
    fun providePopularDealsRepository(popularDealsRepositoryImpl: PopularDealsRepositoryImpl): PopularDealsRepository

    @Binds
    @Singleton
    fun provideSubCategoriesRepository(subCategoriesRepositoryImpl: SubCategoriesRepositoryImpl): SubCategoriesRepository

    @Binds
    @Singleton
    fun provideRecipesRepository(recipesRepositoryImpl: RecipesRepositoryImpl): RecipesRepository

    @Binds
    @Singleton
    fun providePromoCardsRepository(promoCardsRepositoryImpl: PromoCardsRepositoryImpl): PromoCardsRepository

    @Binds
    @Singleton
    fun providePackageRepository(packageRepositoryImpl: PackageRepositoryImpl): PackageRepository

    @Binds
    @Singleton
    fun provideCartRepository(cartRepositoryImpl: CartRepositoryImpl): CartRepository

    @Binds
    @Singleton
    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

}