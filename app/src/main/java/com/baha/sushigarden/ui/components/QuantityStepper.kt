package com.baha.sushigarden.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baha.sushigarden.ui.designsystem.AppColor
import com.baha.sushigarden.ui.designsystem.AppFont

@Composable
fun QuantityStepper(
    quantity: Int,
    onDecrement: () -> Unit,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = 24.dp,
    verticalPadding: Dp = 8.dp,
    spacing: Dp = 16.dp,
) {
    Row(
        modifier =
            modifier
                .clip(RoundedCornerShape(50))
                .background(AppColor.pricePill)
                .padding(horizontal = horizontalPadding, vertical = verticalPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing),
    ) {
        Text(
            text = "−",
            style = AppFont.price.copy(fontSize = 24.sp),
            color = AppColor.textPrimary,
            modifier = Modifier.clickable { onDecrement() },
        )
        Text(
            text = "$quantity",
            style = AppFont.price,
            color = AppColor.textPrimary,
        )
        Text(
            text = "+",
            style = AppFont.price.copy(fontSize = 24.sp),
            color = AppColor.textPrimary,
            modifier = Modifier.clickable { onIncrement() },
        )
    }
}
