package com.baha.sushigarden.features.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baha.sushigarden.ui.designsystem.AppColor
import com.baha.sushigarden.ui.designsystem.AppFont
import com.baha.sushigarden.ui.designsystem.AppSpacing

@Composable
fun ProfileScreen(
    onLogout: () -> Unit,
    viewModel: ProfileViewModel =
        androidx.hilt.navigation.compose
            .hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(AppColor.background)
                .padding(horizontal = AppSpacing.screenMargin),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AppSpacing.lg),
    ) {
        Spacer(modifier = Modifier.height(AppSpacing.md))

        Box(
            modifier =
                Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(AppColor.pricePill),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = null,
                tint = AppColor.textSecondary,
                modifier = Modifier.size(40.dp),
            )
        }

        Text(
            text = state.user?.name ?: "",
            style = AppFont.sectionHeader.copy(fontSize = 20.sp),
            color = AppColor.textPrimary,
        )

        Text(
            text = state.user?.email ?: "",
            style = AppFont.weight,
            color = AppColor.textSecondary,
        )

        Text(
            text = "Мои заказы: ${state.orderCount}",
            style = AppFont.weight,
            color = AppColor.textSecondary,
        )

        OutlinedTextField(
            value = state.phone,
            onValueChange = viewModel::updatePhone,
            placeholder = {
                Text(
                    text = "Телефон",
                    style = AppFont.weight.copy(fontSize = 15.sp),
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done),
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                viewModel.logout()
                onLogout()
            },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AppColor.accent),
        ) {
            Text(
                text = "Выйти",
                style = AppFont.sectionHeader,
                color = AppColor.textPrimary,
            )
        }

        Spacer(modifier = Modifier.height(AppSpacing.lg))
    }
}
