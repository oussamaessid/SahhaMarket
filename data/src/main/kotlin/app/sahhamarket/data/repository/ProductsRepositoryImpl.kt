package app.sahhamarket.data.repository

import app.sahhamarket.data.mapper.ProductMapper
import app.sahhamarket.data.source.local.dao.ProductDao
import app.sahhamarket.data.source.remote.model.ProductDto
import app.sahhamarket.domain.model.Product
import app.sahhamarket.domain.model.Promo
import app.sahhamarket.domain.model.Rating
import app.sahhamarket.domain.model.Status
import app.sahhamarket.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val productMapper: ProductMapper,
) : ProductsRepository {

    override suspend fun getProducts(): Result<List<Product>> {
        // TODO inject the repos and api
        return Result.success(products.map { productMapper.toProduct(it) })
    }

    override suspend fun addProductToCat(product: Product) {
        productDao.addProductToCart(productMapper.toProductEntity(product))
    }

    override suspend fun decrementProductQuantity(product: Product) {
        productDao.decrementProductQuantity(productMapper.toProductEntity(product))
    }
}

private val products = listOf(
    ProductDto(
        id = 1L,
        imageUrl = "file:///android_asset/ic_pomodorini.png",
        title = "Pomodorini 250g (Italy)",
        subTitle = "4.25 SAR / kg",
        price = 1.54F,
        currency = "SAR",
        quantity = 1,
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
        quantity = 1,
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
        quantity = 1,
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
        quantity = 1,
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
        quantity = 1,
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
        quantity = 1,
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
        quantity = 1,
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