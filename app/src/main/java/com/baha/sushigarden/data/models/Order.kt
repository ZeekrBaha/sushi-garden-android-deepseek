package com.baha.sushigarden.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class OrderLine(
    val name: String,
    val quantity: Int,
    val priceRub: Int,
)

data class Order(
    val id: String,
    val createdAt: Long,
    val totalRub: Int,
    val lines: List<OrderLine>,
)

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey val id: String,
    val createdAt: Long,
    val totalRub: Int,
    val linesJson: String,
)
