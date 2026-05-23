package com.baha.sushigarden.features.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.baha.sushigarden.data.models.Product
import com.baha.sushigarden.ui.designsystem.AppColor
import com.baha.sushigarden.ui.designsystem.AppFont
import com.baha.sushigarden.ui.designsystem.AppSpacing

@Composable
fun CatalogScreen(
    onProductClick: (String) -> Unit,
    viewModel: CatalogViewModel =
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
        LazyRow(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = AppSpacing.screenMargin),
            horizontalArrangement = Arrangement.spacedBy(AppSpacing.sm),
            contentPadding = PaddingValues(vertical = AppSpacing.sm),
        ) {
            items(state.categories) { category ->
                val isSelected = category == state.selectedCategory
                Text(
                    text = category.displayName,
                    style = AppFont.sectionHeader,
                    color = if (isSelected) AppColor.textPrimary else AppColor.inactive,
                    modifier =
                        Modifier
                            .clickable { viewModel.selectCategory(category) }
                            .padding(vertical = AppSpacing.sm),
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = AppSpacing.screenMargin),
            horizontalArrangement = Arrangement.spacedBy(AppSpacing.md),
            verticalArrangement = Arrangement.spacedBy(AppSpacing.md),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(state.products, key = { it.id }) { product ->
                ProductCardView(
                    product = product,
                    onClick = { onProductClick(product.id) },
                )
            }
        }
    }
}

@Composable
fun ProductCardView(
    product: Product,
    onClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable { onClick() },
        horizontalAlignment = Alignment.Start,
    ) {
        AsyncImage(
            model = product.imageName,
            contentDescription = product.name,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(AppSpacing.cardCorner)),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(AppSpacing.xs))

        Text(
            text = product.name,
            style = AppFont.productTitle,
            color = AppColor.textPrimary,
        )

        Text(
            text = "${product.weightGrams}г",
            style = AppFont.weight,
            color = AppColor.textSecondary,
        )

        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(AppSpacing.cardCorner))
                    .background(AppColor.pricePill)
                    .padding(horizontal = AppSpacing.sm, vertical = AppSpacing.xs),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "${product.priceRub} ₽",
                style = AppFont.price,
                color = AppColor.textPrimary,
                textAlign = TextAlign.Center,
            )
        }
    }
}
