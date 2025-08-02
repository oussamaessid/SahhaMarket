package app.sahhamarket.data.repository

import app.sahhamarket.data.mapper.PackageMapper
import app.sahhamarket.data.source.remote.model.PackageDto
import app.sahhamarket.domain.model.Package
import app.sahhamarket.domain.repository.PackageRepository
import javax.inject.Inject

class PackageRepositoryImpl @Inject constructor(
    private val packageMapper: PackageMapper
) : PackageRepository {

    override suspend fun getWeeklyPackages(): List<Package> {
        val weeklyPackages = listOf(
            PackageDto(
                id = 1,
                imageUrl = "file:///android_asset/ic_pomodorini.png",
                title = "Two Person",
                subTitle = "Lorem ipsum text here",
                originalPrice = "2.40",
                discountedPrice = "1.60",
                discount = 15,
                isDonate = false
            ),
            PackageDto(
                id = 2,
                imageUrl = "file:///android_asset/ic_packages_weekly.png",
                title = "Four Person",
                subTitle = "Lorem ipsum text here",
                originalPrice = "1.80",
                discountedPrice = "1.20",
                discount = 15,
                isDonate = false
            ),
            PackageDto(
                id = 3,
                imageUrl = "file:///android_asset/ic_cherrytomaten.png",
                title = "Six Persons",
                subTitle = "Lorem ipsum text here",
                originalPrice = "2.20",
                discountedPrice = "1.60",
                discount = 15,
                isDonate = false
            )
        )
        return weeklyPackages.map { packageMapper.toPackage(it) }
    }

    override suspend fun getAlkhirPackages(): List<Package> {
        val alkhirPackages = listOf(
            PackageDto(
                id = 4,
                imageUrl = "file:///android_asset/ic_packages_alkhir.png",
                title = "Package L",
                subTitle = "Lorem ipsum",
                originalPrice = "",
                discountedPrice = "0.95",
                discount = null,
                isDonate = true
            ),
            PackageDto(
                id = 5,
                imageUrl = "file:///android_asset/ic_packages_alkhir.png",
                title = "Package XL",
                subTitle = "Lorem ipsum",
                originalPrice = "",
                discountedPrice = "0.85",
                discount = null,
                isDonate = true
            ),
            PackageDto(
                id = 6,
                imageUrl = "file:///android_asset/ic_packages_alkhir.png",
                title = "Package XXL",
                subTitle = "Lorem ipsum",
                originalPrice = "",
                discountedPrice = "1.60",
                discount = null,
                isDonate = true
            )
        )
        return alkhirPackages.map { packageMapper.toPackage(it) }
    }

    override suspend fun getLowCaloriePackages(): List<Package> {
        val lowCaloriePackages = listOf(
            PackageDto(
                id = 7,
                imageUrl = "file:///android_asset/ic_cherrytomaten.png",
                title = "Red Apple Text",
                subTitle = "500g",
                originalPrice = "1.90",
                discountedPrice = "1.35",
                discount = 5,
                isDonate = false
            ),
            PackageDto(
                id = 8,
                imageUrl = "file:///android_asset/ic_moscow_vr_tomatoes.png",
                title = "Yellow Apple Text",
                subTitle = "500g",
                originalPrice = "1.80",
                discountedPrice = "1.25",
                discount = 25,
                isDonate = false
            ),
            PackageDto(
                id = 9,
                imageUrl = "file:///android_asset/ic_roma_vf_tomatoes.png",
                title = "Banans Text here",
                subTitle = "Text here",
                originalPrice = "3.10",
                discountedPrice = "2.60",
                discount = 10,
                isDonate = false
            )
        )
        return lowCaloriePackages.map { packageMapper.toPackage(it) }
    }

    override suspend fun getZerdaPackages(): List<Package> {
        val zerdaPackages = listOf(
            PackageDto(
                id = 10,
                imageUrl = "file:///android_asset/ic_roma_vf_tomatoes.png",
                title = "Zarda for 20 person",
                subTitle = "For 20 persons",
                originalPrice = "5.40",
                discountedPrice = "4.90",
                discount = 10,
                isDonate = false
            ),
            PackageDto(
                id = 11,
                imageUrl = "file:///android_asset/ic_moscow_vr_tomatoes.png",
                title = "Zarda Machawi",
                subTitle = "For 20 persons",
                originalPrice = "3.85",
                discountedPrice = "3.15",
                discount = 25,
                isDonate = false
            ),
            PackageDto(
                id = 12,
                imageUrl = "file:///android_asset/ic_moscow_vr_tomatoes.png",
                title = "Zarda koskous",
                subTitle = "For 20 persons",
                originalPrice = "6.95",
                discountedPrice = "6.10",
                discount = 15,
                isDonate = false
            )
        )
        return zerdaPackages.map { packageMapper.toPackage(it) }
    }
}