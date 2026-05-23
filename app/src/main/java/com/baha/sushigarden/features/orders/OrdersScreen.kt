package com.baha.sushigarden.features.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.baha.sushigarden.data.models.Order
import com.baha.sushigarden.ui.designsystem.AppColor
import com.baha.sushigarden.ui.designsystem.AppFont
import com.baha.sushigarden.ui.designsystem.AppSpacing

@Composable
fun OrdersScreen(
    onOrderClick: (String) -> Unit,
    viewModel: OrdersViewModel =
        androidx.hilt.navigation.compose
            .hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(AppColor.background),
    ) {
        if (state.orders.isEmpty()) {
            Box(
                modifier =
                    Modifier
                        .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Заказов пока нет",
                    style = AppFont.sectionHeader,
                    color = AppColor.textSecondary,
                )
            }
        } else {
            LazyColumn(
                contentPadding =
                    androidx.compose.foundation.layout.PaddingValues(
                        horizontal = AppSpacing.screenMargin,
                        vertical = AppSpacing.sm,
                    ),
                verticalArrangement = Arrangement.spacedBy(AppSpacing.sm),
            ) {
                items(state.orders, key = { it.id }) { order ->
                    OrderRow(order = order, onClick = { onOrderClick(order.id) })
                }
            }
        }
    }
}

@Composable
private fun OrderRow(
    order: Order,
    onClick: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(horizontal = AppSpacing.screenMargin, vertical = AppSpacing.sm),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "Заказ №${order.id.takeLast(6)}",
            style = AppFont.productTitle,
            color = AppColor.textPrimary,
        )
        Text(
            text = "${order.totalRub} ₽",
            style = AppFont.weight,
            color = AppColor.textSecondary,
        )
    }
}

@Composable
fun OrderDetailScreen(
    orderId: String,
    onBack: () -> Unit,
    viewModel: OrdersViewModel =
        androidx.hilt.navigation.compose
            .hiltViewModel(),
) {
    LaunchedEffect(orderId) {
        viewModel.loadOrderDetail(orderId)
    }

    val state by viewModel.detailState.collectAsState()
    val order = state.order ?: return

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(AppColor.background),
    ) {
        LazyColumn(
            contentPadding =
                androidx.compose.foundation.layout
                    .PaddingValues(horizontal = AppSpacing.screenMargin),
            verticalArrangement = Arrangement.spacedBy(AppSpacing.sm),
        ) {
            items(order.lines) { line ->
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = AppSpacing.sm),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column {
                        Text(
                            text = line.name,
                            style = AppFont.productTitle,
                            color = AppColor.textPrimary,
                        )
                        Text(
                            text = "× ${line.quantity}",
                            style = AppFont.weight,
                            color = AppColor.textSecondary,
                        )
                    }
                    Text(
                        text = "${line.priceRub * line.quantity} ₽",
                        style = AppFont.weight,
                        color = AppColor.textSecondary,
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(AppSpacing.sm))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Итого",
                        style = AppFont.price,
                        color = AppColor.textPrimary,
                    )
                    Text(
                        text = "${order.totalRub} ₽",
                        style = AppFont.price,
                        color = AppColor.accent,
                    )
                }
            }
        }
    }
}
