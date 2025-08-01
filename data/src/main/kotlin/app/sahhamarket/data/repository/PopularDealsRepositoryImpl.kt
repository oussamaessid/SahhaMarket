package app.sahhamarket.data.repository

import app.sahhamarket.data.mapper.ProductMapper
import app.sahhamarket.data.source.local.dao.ProductDao
import app.sahhamarket.data.source.remote.model.ProductDto
import app.sahhamarket.domain.model.Product
import app.sahhamarket.domain.model.Promo
import app.sahhamarket.domain.model.Rating
import app.sahhamarket.domain.model.Status
import app.sahhamarket.domain.repository.PopularDealsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PopularDealsRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val productMapper: ProductMapper,
) : PopularDealsRepository {

    override fun getPopularDeals(): Flow<Result<List<Product>>> = productDao.getProducts()
        .map { localEntities ->
            val localProducts = localEntities.map { productMapper.toProduct(it) }

            val finalList = if (localProducts.isEmpty()) {
                // ⚠️ Aucun produit en base → on montre les produits de l’API
                products.map { productMapper.toProduct(it) }
            } else {
                // 🔁 Fusion : priorité au produit local s’il existe
                products.map { remote ->
                    localProducts.find { it.id == remote.id } ?: productMapper.toProduct(remote)
                }
            }

            Result.success(finalList)
        }
    /**
    override fun getPopularDeals(): Flow<Result<List<Product>>> = flow {
    // TODO inject the repos and api
    productDao.getProducts().collectLatest {productsLocal->
    emit(Result.success(
    products.map { product ->
    val productExist = productsLocal.findLast { it.id == product.id }
    if (productExist != null) {
    productMapper.toProduct(productExist)
    } else {
    productMapper.toProduct(product)
    }
    }
    ))
    }
    }**/
}

private val products = listOf(
    ProductDto(
        id = 1L,
        imageUrl = "file:///android_asset/ic_pomodorini.png",
        title = "Pomodorini 250g (Italy)",
        subTitle = "4.25 SAR / kg",
        price = 1.54F,
        currency = "SAR",
        quantity = 0,
        promo = Promo(
            value = 10,
            price = 1.39F
        ),
        rating = Rating(
            value = 4.7F,
            number = 123
        ),
        status = Status.AVAILABLE
    ),
    ProductDto(
        id = 2L,
        imageUrl = "file:///android_asset/ic_h_tomatoes.png",
        title = "hTomatoes 250g",
        subTitle = "3.45 SAR / kg",
        price = 1.80F,
        currency = "SAR",
        quantity = 0,
        promo = Promo(
            value = 10,
            price = 1.62F
        ),
        rating = Rating(
            value = 4.6F,
            number = 17
        ),
        status = Status.AVAILABLE
    ),
    ProductDto(
        id = 3L,
        imageUrl = "file:///android_asset/ic_cherrytomaten.png",
        title = "Cherrytomaten 250g (Marokko)",
        subTitle = "2.20 SAR / kg",
        price = 0.95F,
        currency = "SAR",
        quantity = 0,
        promo = Promo(
            value = 7,
            price = 0.88F
        ),
        rating = Rating(
            value = 5.0F,
            number = 99
        ),
        status = Status.UNAVAILABLE
    ),
    ProductDto(
        id = 4L,
        imageUrl = "file:///android_asset/ic_roma_vf_tomatoes.png",
        title = "Roma VF Tomatoes",
        subTitle = "2.85 SAR / kg",
        price = 1.60F,
        currency = "SAR",
        quantity = 0,
        promo = Promo(
            value = 7,
            price = 1.49F
        ),
        rating = Rating(
            value = 4.7F,
            number = 9
        ),
        status = Status.AVAILABLE
    ),
    ProductDto(
        id = 5L,
        imageUrl = "file:///android_asset/ic_moscow_vr_tomatoes.png",
        title = "Moscow VR Tomatoes",
        subTitle = "1.25 SAR / kg",
        price = 2.00F,
        currency = "SAR",
        quantity = 0,
        promo = Promo(
            value = 5,
            price = 1.90F
        ),
        rating = Rating(
            value = 4.1F,
            number = 27
        ),
        status = Status.AVAILABLE
    ),
    ProductDto(
        id = 6L,
        imageUrl = "file:///android_asset/ic_calypso_tomato.png",
        title = "Calypso Tomato 200 gr",
        subTitle = "7.5 SAR / kg",
        price = 1.50F,
        currency = "SAR",
        quantity = 0,
        promo = Promo(
            value = 5,
            price = 1.43F
        ),
        rating = Rating(
            value = 3.5F,
            number = 149
        ),
        status = Status.AVAILABLE
    ),
    ProductDto(
        id = 7L,
        imageUrl = "file:///android_asset/ic_calypso_tomato.png",
        title = "Calypso Tomato 200 gr",
        subTitle = "7.5 SAR / kg",
        price = 1.50F,
        currency = "SAR",
        quantity = 0,
        promo = Promo(
            value = 5,
            price = 0.075F
        ),
        rating = Rating(
            value = 4.9F,
            number = 1345
        ),
        status = Status.AVAILABLE
    )
)