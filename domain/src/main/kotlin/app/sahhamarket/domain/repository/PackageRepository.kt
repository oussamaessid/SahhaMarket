package app.sahhamarket.domain.repository

import app.sahhamarket.domain.model.Package

interface PackageRepository {
    suspend fun getWeeklyPackages(): List<Package>
    suspend fun getAlkhirPackages(): List<Package>
    suspend fun getLowCaloriePackages(): List<Package>
    suspend fun getZerdaPackages(): List<Package>
}