package app.sahhamarket.data.repository

import app.sahhamarket.data.mapper.ProductMapper
import app.sahhamarket.data.mapper.RecipeMapper
import app.sahhamarket.data.source.local.dao.ProductDao
import app.sahhamarket.data.source.remote.model.IngredientDto
import app.sahhamarket.data.source.remote.model.ProductDto
import app.sahhamarket.data.source.remote.model.ReceiptDto
import app.sahhamarket.domain.model.Product
import app.sahhamarket.domain.model.ProductDetails
import app.sahhamarket.domain.model.Promo
import app.sahhamarket.domain.model.Rating
import app.sahhamarket.domain.model.Status
import app.sahhamarket.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val productMapper: ProductMapper,
    private val recipeMapper: RecipeMapper,
) : ProductsRepository {

    override fun getProducts(): Flow<Result<List<Product>>> = productDao.getProducts()
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

    override fun getProduct(productId: Long): Flow<Result<ProductDetails>> =
        productDao.getProductById(productId)
            .map { localProduct ->

                if (localProduct != null) {
                    // 🔁 Fusion : priorité au produit local s’il existe
                    Result.success(
                        ProductDetails(
                            product = productMapper.toProduct(localProduct),
                            similarProducts = products.map { product ->
                                productMapper.toProduct(
                                    product
                                )
                            },
                            recipes = receipts.map { recipe ->
                                recipeMapper.toRecipe(recipe)
                            }
                        ))
                } else {
                    // ⚠️ Aucun produit en base → on montre le produit de l’API
                    val remoteProduct = products.find { product -> product.id == productId }
                    if (remoteProduct != null) {
                        Result.success(
                            ProductDetails(
                                product = productMapper.toProduct(remoteProduct),
                                similarProducts = products.map { product ->
                                    productMapper.toProduct(
                                        product
                                    )
                                },
                                recipes = receipts.map { recipe -> recipeMapper.toRecipe(recipe) })
                        )
                    } else {
                        Result.failure(Exception("No product"))
                    }
                }
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

private val receipts = listOf(
    ReceiptDto(
        id = 1L,
        imageUrl = "file:///android_asset/ic_receipt_1.png",
        title = "Spagheti with Pesto Genovese and Basilico di Parma.",
        ingredients = listOf(
            IngredientDto(
                id = 1L,
                name = "Cherrytoma-ten 250g (Marokko)",
                imageUrl = "file:///android_asset/ic_moscow_vr_tomatoes.png"
            ),
            IngredientDto(
                id = 2L,
                name = "Salt lorem ipsum text until here",
                imageUrl = "file:///android_asset/ic_moscow_vr_tomatoes.png"
            )
        ),
        price = 5.00F,
        currency = "SAR",
        time = "25 min"
    ),
    ReceiptDto(
        id = 1L,
        imageUrl = "file:///android_asset/ic_receipt_1.png",
        title = "Avocado Toast with Homemade Guacamole, Feta Cheese and tomato.",
        ingredients = listOf(
            IngredientDto(
                id = 1L,
                name = "Cherrytoma-ten 250g (Marokko)",
                imageUrl = "file:///android_asset/ic_moscow_vr_tomatoes.png"
            ),
            IngredientDto(
                id = 2L,
                name = "Salt lorem ipsum text until here",
                imageUrl = "file:///android_asset/ic_moscow_vr_tomatoes.png"
            ),
            IngredientDto(
                id = 3L,
                name = "Salt lorem ipsum text until here",
                imageUrl = "file:///android_asset/ic_moscow_vr_tomatoes.png"
            ),
            IngredientDto(
                id = 4L,
                name = "Salt lorem ipsum text until here",
                imageUrl = "file:///android_asset/ic_moscow_vr_tomatoes.png"
            )
        ),
        price = 6.45F,
        currency = "SAR",
        time = "20 min"
    ),
)