package app.sahhamarket.domain.datastore

interface PreferenceStorage {
    suspend fun setIsFirstInstall()
    suspend fun getIsFirstInstall(): Boolean

}