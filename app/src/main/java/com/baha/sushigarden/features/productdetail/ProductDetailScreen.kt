package com.baha.sushigarden.features.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.baha.sushigarden.ui.components.rememberImageRequest
import com.baha.sushigarden.ui.designsystem.AppColor
import com.baha.sushigarden.ui.designsystem.AppFont
import com.baha.sushigarden.ui.designsystem.AppSpacing

@Composable
fun ProductDetailScreen(
    productId: String,
    onBack: () -> Unit,
    viewModel: ProductDetailViewModel =
        androidx.hilt.navigation.compose
            .hiltViewModel(),
) {
    LaunchedEffect(productId) {
        viewModel.loadProduct(productId)
    }

    val state by viewModel.state.collectAsState()
    val product = state.product ?: return

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(AppColor.background)
                .padding(horizontal = AppSpacing.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = rememberImageRequest(product.imageName),
            contentDescription = product.name,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(280.dp),
            contentScale = ContentScale.Fit,
        )

        Text(
            text = product.name,
            style = AppFont.sectionHeader.copy(fontSize = 22.sp),
            color = AppColor.textPrimary,
        )

        Text(
            text = "${product.weightGrams}г",
            style = AppFont.weight,
            color = AppColor.textSecondary,
        )

        Spacer(modifier = Modifier.height(AppSpacing.sm))

        Text(
            text = product.description,
            style = AppFont.weight.copy(fontSize = 14.sp),
            color = AppColor.textSecondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = AppSpacing.lg),
        )

        Spacer(modifier = Modifier.height(AppSpacing.md))

        Row(
            modifier =
                Modifier
                    .clip(RoundedCornerShape(50))
                    .background(AppColor.pricePill)
                    .padding(horizontal = AppSpacing.lg, vertical = AppSpacing.sm),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(AppSpacing.md),
        ) {
            Text(
                text = "−",
                style = AppFont.price.copy(fontSize = 24.sp),
                color = AppColor.textPrimary,
                modifier = Modifier.clickable { viewModel.decrementQuantity() },
            )
            Text(
                text = "${state.quantity}",
                style = AppFont.price,
                color = AppColor.textPrimary,
            )
            Text(
                text = "+",
                style = AppFont.price.copy(fontSize = 24.sp),
                color = AppColor.textPrimary,
                modifier = Modifier.clickable { viewModel.incrementQuantity() },
            )
        }

        Spacer(modifier = Modifier.height(AppSpacing.md))

        Button(
            onClick = {
                viewModel.addToCart()
                onBack()
            },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = AppSpacing.screenMargin),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AppColor.accent),
        ) {
            Text(
                text = "Оформить заказ · ${product.priceRub * state.quantity} ₽",
                style = AppFont.sectionHeader,
                color = AppColor.textPrimary,
            )
        }
    }
}
