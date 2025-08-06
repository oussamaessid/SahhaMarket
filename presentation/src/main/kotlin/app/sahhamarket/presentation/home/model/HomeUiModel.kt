package app.sahhamarket.presentation.home.model

import app.sahhamarket.domain.model.Category
import app.sahhamarket.domain.model.Product
import app.sahhamarket.domain.model.PromoCard
import app.sahhamarket.domain.model.Receipt
import app.sahhamarket.domain.model.SubCategory

sealed interface PromoCardUiModel {
    data object Loading : PromoCardUiModel
    data object Error : PromoCardUiModel
    data class Success(val promoList: List<PromoCard> = emptyList()) : PromoCardUiModel
}

sealed interface CategoryUiModel {
    data object Loading : CategoryUiModel
    data object Error : CategoryUiModel
    data class Success(val categories: List<Category> = emptyList()) : CategoryUiModel
}

sealed interface SubCategoryUiModel {
    data object Loading : SubCategoryUiModel
    data object Error : SubCategoryUiModel
    data class Success(val subCategories: List<SubCategory> = emptyList()) : SubCategoryUiModel
}

sealed interface ProductUiModel {
    data object Loading : ProductUiModel
    data object Error : ProductUiModel
    data class Success(val products: List<Product> = emptyList()) : ProductUiModel
}

sealed interface PopularDealsUiModel {
    data object Loading : PopularDealsUiModel
    data object Error : PopularDealsUiModel
    data class Success(val popularDeals: List<Product> = emptyList()) : PopularDealsUiModel
}

sealed interface ReceiptUiModel {
    data object Loading : ReceiptUiModel
    data object Error : ReceiptUiModel
    data class Success(val recipes: List<Receipt> = emptyList()) : ReceiptUiModel
}