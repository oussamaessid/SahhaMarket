package app.sahhamarket.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import app.sahhamarket.data.source.local.entities.AddressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAddress(addressEntity: AddressEntity)

    @Query("SELECT * FROM addressentity")
    fun getAddressList(): Flow<List<AddressEntity>>

    @Query("SELECT * FROM addressentity WHERE id=:addressId")
    suspend fun getAddressById(addressId: Long): AddressEntity

    @Query("SELECT * FROM addressentity WHERE isDefault=1")
    suspend fun getDefaultAddress(): AddressEntity

    @Query("UPDATE addressentity SET isDefault = 0")
    suspend fun clearSelection()

    @Query("UPDATE addressentity SET isDefault = 1 WHERE id = :addressId")
    suspend fun setDefaultAddress(addressId: Long)

    @Query("DELETE FROM addressentity WHERE id=:addressId")
    suspend fun deleteAddress(addressId: Long)

    @Transaction
    suspend fun addNewAddress(addressEntity: AddressEntity) {
        clearSelection()
        saveAddress(addressEntity)
    }

    @Transaction
    suspend fun updateSelection(addressId: Long) {
        clearSelection()
        setDefaultAddress(addressId)
    }
}