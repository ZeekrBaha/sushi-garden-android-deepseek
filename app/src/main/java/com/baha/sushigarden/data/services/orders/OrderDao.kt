package com.baha.sushigarden.data.services.orders

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.baha.sushigarden.data.models.OrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Query("SELECT * FROM orders ORDER BY createdAt DESC")
    fun getAllOrders(): Flow<List<OrderEntity>>

    @Query("SELECT * FROM orders WHERE id = :id")
    suspend fun getOrderById(id: String): OrderEntity?

    @Insert
    suspend fun insertOrder(order: OrderEntity)
}
