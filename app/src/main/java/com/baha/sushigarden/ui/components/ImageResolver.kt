package com.baha.sushigarden.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.baha.sushigarden.R

fun resolveImageRes(name: String): Int = when (name) {
    "product_hikari" -> R.drawable.product_hikari
    "product_la" -> R.drawable.product_la
    "product_idaho" -> R.drawable.product_idaho
    "product_osaka" -> R.drawable.product_osaka
    "banner_promo_1" -> R.drawable.banner_promo_1
    "banner_promo_2" -> R.drawable.banner_promo_2
    else -> R.drawable.product_hikari
}

@Composable
fun rememberImageRequest(imageName: String): ImageRequest {
    val context = LocalContext.current
    return ImageRequest.Builder(context)
        .data(resolveImageRes(imageName))
        .memoryCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.DISABLED)
        .build()
}
