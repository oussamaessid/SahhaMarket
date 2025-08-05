package app.sahhamarket.domain.usecase.packages

import app.sahhamarket.domain.model.Package
import app.sahhamarket.domain.repository.PackageRepository
import javax.inject.Inject

class GetPackagesUseCase @Inject constructor(
    private val repository: PackageRepository
) {
    suspend fun getWeeklyPackages(): List<Package> {
        return repository.getWeeklyPackages()
    }

    suspend fun getAlkhirPackages(): List<Package> {
        return repository.getAlkhirPackages()
    }

    suspend fun getLowCaloriePackages(): List<Package> {
        return repository.getLowCaloriePackages()
    }

    suspend fun getZerdaPackages(): List<Package> {
        return repository.getZerdaPackages()
    }
}