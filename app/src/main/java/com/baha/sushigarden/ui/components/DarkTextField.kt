package com.baha.sushigarden.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baha.sushigarden.ui.designsystem.AppColor
import com.baha.sushigarden.ui.designsystem.AppFont

@Composable
fun DarkTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = AppFont.weight.copy(fontSize = 15.sp),
                color = AppColor.textSecondary,
            )
        },
        modifier = modifier,
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
