package com.baha.sushigarden.features.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baha.sushigarden.ui.designsystem.AppColor
import com.baha.sushigarden.ui.designsystem.AppFont
import com.baha.sushigarden.ui.designsystem.AppSpacing

@Composable
fun CheckoutScreen(
    onOrderPlaced: () -> Unit,
    viewModel: CheckoutViewModel =
        androidx.hilt.navigation.compose
            .hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            onOrderPlaced()
        }
    }

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(AppColor.background),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = AppSpacing.screenMargin),
            verticalArrangement = Arrangement.spacedBy(AppSpacing.md),
        ) {
            Spacer(modifier = Modifier.height(AppSpacing.sm))

            CheckoutField(
                value = state.name,
                placeholder = "Имя",
                onValueChange = viewModel::updateName,
            )
            CheckoutField(
                value = state.phone,
                placeholder = "Телефон",
                onValueChange = viewModel::updatePhone,
                keyboardType = KeyboardType.Phone,
            )
            CheckoutField(
                value = state.email,
                placeholder = "Почта",
                onValueChange = viewModel::updateEmail,
                keyboardType = KeyboardType.Email,
            )

            Spacer(modifier = Modifier.height(AppSpacing.sm))

            CheckoutRow("Сумма заказа", "${state.subtotal} ₽")
            CheckoutRow("Доставка", "${state.deliveryFee} ₽")
            CheckoutRow("Сервисный сбор", "${state.serviceFee} ₽")

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
                    text = "${state.total} ₽",
                    style = AppFont.price,
                    color = AppColor.accent,
                )
            }

            state.error?.let { error ->
                Text(
                    text = error,
                    color = AppColor.accent,
                    style = AppFont.weight.copy(fontSize = 12.sp),
                )
            }

            Button(
                onClick = viewModel::placeOrder,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AppColor.accent),
                enabled = !state.isLoading,
            ) {
                Text(
                    text = "Подтвердить",
                    style = AppFont.sectionHeader,
                    color = AppColor.textPrimary,
                )
            }

            Spacer(modifier = Modifier.height(AppSpacing.lg))
        }

        if (state.isLoading) {
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.4f)),
                contentAlignment = Alignment.Center,
            ) {
                Text("Загрузка...", color = AppColor.textPrimary)
            }
        }
    }
}

@Composable
private fun CheckoutField(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = AppFont.weight.copy(fontSize = 14.sp),
                color = AppColor.textSecondary,
            )
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors =
            OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AppColor.inactive,
                unfocusedBorderColor = AppColor.inactive,
                focusedContainerColor = AppColor.tabBar,
                unfocusedContainerColor = AppColor.tabBar,
                cursorColor = AppColor.textPrimary,
                focusedTextColor = AppColor.textPrimary,
                unfocusedTextColor = AppColor.textPrimary,
            ),
        textStyle = AppFont.weight.copy(fontSize = 15.sp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = ImeAction.Next),
    )
}

@Composable
private fun CheckoutRow(
    label: String,
    value: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = AppFont.weight,
            color = AppColor.textSecondary,
        )
        Text(
            text = value,
            style = AppFont.weight,
            color = AppColor.textSecondary,
        )
    }
}
