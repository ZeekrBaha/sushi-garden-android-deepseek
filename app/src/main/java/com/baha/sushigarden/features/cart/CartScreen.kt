package com.baha.sushigarden.features.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.baha.sushigarden.data.models.CartItem
import com.baha.sushigarden.ui.designsystem.AppColor
import com.baha.sushigarden.ui.designsystem.AppFont
import com.baha.sushigarden.ui.designsystem.AppSpacing

@Composable
fun CartScreen(
    onCheckout: () -> Unit,
    viewModel: CartViewModel =
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
        if (state.items.isEmpty()) {
            Box(
                modifier =
                    Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Корзина пуста",
                    style = AppFont.sectionHeader,
                    color = AppColor.textSecondary,
                )
            }
        } else {
            val totalItems = state.items.sumOf { it.quantity }
            val totalWeight = state.items.sumOf { it.product.weightGrams * it.quantity }

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = AppSpacing.screenMargin, vertical = AppSpacing.sm),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Корзина",
                    style = AppFont.sectionHeader,
                    color = AppColor.textPrimary,
                )
                Text(
                    text = "$totalItems позиций / ${totalWeight}г",
                    style = AppFont.weight,
                    color = AppColor.textSecondary,
                )
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(horizontal = AppSpacing.screenMargin),
                verticalArrangement = Arrangement.spacedBy(AppSpacing.md),
            ) {
                items(state.items, key = { it.id }) { item ->
                    CartItemRow(
                        item = item,
                        onIncrement = { viewModel.incrementQuantity(item.id) },
                        onDecrement = { viewModel.decrementQuantity(item.id) },
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(AppSpacing.md))
                    Text(
                        text = "Добавить еще",
                        style = AppFont.sectionHeader,
                        color = AppColor.textPrimary,
                    )
                    state.addOns.forEach { addOn ->
                        val isSelected = state.selectedAddOnIds.contains(addOn.id)
                        Row(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .clickable { viewModel.toggleAddOn(addOn) }
                                    .padding(vertical = AppSpacing.sm),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = addOn.name,
                                style = AppFont.weight,
                                color = AppColor.textSecondary,
                            )
                            Text(
                                text = "${addOn.priceRub} ₽",
                                style = AppFont.weight,
                                color = if (isSelected) AppColor.textPrimary else AppColor.textSecondary,
                                modifier =
                                    Modifier
                                        .clip(RoundedCornerShape(50))
                                        .background(if (isSelected) AppColor.accent else AppColor.pricePill)
                                        .padding(horizontal = 12.dp, vertical = 4.dp),
                            )
                        }
                    }
                }
            }

            Button(
                onClick = onCheckout,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = AppSpacing.screenMargin)
                        .padding(bottom = AppSpacing.sm),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AppColor.accent),
            ) {
                Text(
                    text = "Оформить заказ · ${state.totalRub} ₽",
                    style = AppFont.sectionHeader,
                    color = AppColor.textPrimary,
                )
            }
        }
    }
}

@Composable
private fun CartItemRow(
    item: CartItem,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = AppSpacing.sm),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = item.product.imageName,
            contentDescription = item.product.name,
            modifier =
                Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(AppSpacing.sm))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.product.name,
                style = AppFont.productTitle,
                color = AppColor.textPrimary,
            )
            Text(
                text = "${item.lineTotal} ₽",
                style = AppFont.weight,
                color = AppColor.textSecondary,
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(AppSpacing.md),
        ) {
            Text(
                text = "−",
                style = AppFont.productTitle.copy(fontSize = 20.sp),
                color = AppColor.textPrimary,
                modifier =
                    Modifier
                        .clip(RoundedCornerShape(50))
                        .background(AppColor.pricePill)
                        .clickable { onDecrement() }
                        .padding(8.dp),
            )
            Text(
                text = "${item.quantity}",
                style = AppFont.productTitle,
                color = AppColor.textPrimary,
            )
            Text(
                text = "+",
                style = AppFont.productTitle.copy(fontSize = 20.sp),
                color = AppColor.textPrimary,
                modifier =
                    Modifier
                        .clip(RoundedCornerShape(50))
                        .background(AppColor.pricePill)
                        .clickable { onIncrement() }
                        .padding(8.dp),
            )
        }
    }
}
