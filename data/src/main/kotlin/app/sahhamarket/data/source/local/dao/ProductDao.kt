package app.sahhamarket.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import app.sahhamarket.data.source.local.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM productentity")
    fun getProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM productentity WHERE id=:productId")
    fun getProductById(productId: Long): Flow<ProductEntity?>

    @Query("SELECT EXISTS(SELECT 1 FROM productentity WHERE id = :productId)")
    suspend fun checkProductExists(productId: Long): Boolean

    @Query("DELETE FROM productentity WHERE id=:productId")
    suspend fun deleteProduct(productId: Long)

    @Query("UPDATE productentity SET quantity = quantity + 1 WHERE id = :productId")
    suspend fun incrementProductQuantity(productId: Long)

    @Query("UPDATE productentity SET quantity = quantity - 1 WHERE id = :productId")
    suspend fun decrementProductQuantity(productId: Long)

    @Transaction
    suspend fun decrementProductQuantity(productEntity: ProductEntity) {
        if (productEntity.quantity > 1) {
            decrementProductQuantity(productEntity.id)
        } else {
            deleteProduct(productId = productEntity.id)
        }
    }

    @Transaction
    suspend fun addProductToCart(productEntity: ProductEntity) {
        if (checkProductExists(productId = productEntity.id)) {
            incrementProductQuantity(productId = productEntity.id)
        } else {
            saveProduct(productEntity.copy(quantity = 1))
        }
    }
}