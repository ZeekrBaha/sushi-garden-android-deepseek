package com.baha.sushigarden.features.promotions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.baha.sushigarden.ui.designsystem.AppColor
import com.baha.sushigarden.ui.designsystem.AppSpacing

@Composable
fun PromotionsScreen(
    viewModel: PromotionsViewModel =
        androidx.hilt.navigation.compose
            .hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .background(AppColor.background),
        contentPadding = PaddingValues(horizontal = AppSpacing.screenMargin),
        verticalArrangement = Arrangement.spacedBy(AppSpacing.md),
    ) {
        items(state.banners, key = { it.id }) { banner ->
            AsyncImage(
                model = banner.imageName,
                contentDescription = null,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(AppSpacing.bannerCorner)),
                contentScale = ContentScale.Fit,
            )
        }
    }
}
