package com.baha.sushigarden.features.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baha.sushigarden.ui.designsystem.AppColor
import com.baha.sushigarden.ui.designsystem.AppFont
import com.baha.sushigarden.ui.designsystem.AuthColor

@Composable
fun AuthScreen(
    viewModel: AuthViewModel,
    onAuthSuccess: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

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
                    .padding(horizontal = 22.dp),
        ) {
            Spacer(modifier = Modifier.weight(0.2f))

            Text(
                text = if (state.isRegisterMode) "Регистрация" else "Войти",
                style = AppFont.sectionHeader.copy(fontSize = 29.sp),
                color = AppColor.textPrimary,
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(Color.White)
                        .padding(horizontal = 22.dp, vertical = 34.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
            ) {
                if (state.isRegisterMode) {
                    AuthField(
                        label = "ИМЯ",
                        value = state.name,
                        placeholder = "Александр",
                        onValueChange = viewModel::updateName,
                    )
                }

                AuthField(
                    label = "ПОЧТА",
                    value = state.email,
                    placeholder = "example@gmail.com",
                    onValueChange = viewModel::updateEmail,
                    keyboardType = KeyboardType.Email,
                )

                AuthField(
                    label = "ПАРОЛЬ",
                    value = state.password,
                    placeholder = "**********",
                    onValueChange = viewModel::updatePassword,
                    isPassword = true,
                    isPasswordVisible = state.isPasswordVisible,
                    onTogglePassword = viewModel::togglePasswordVisibility,
                )

                if (state.isRegisterMode) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                    ) {
                        Box(
                            modifier =
                                Modifier
                                    .size(18.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .border(1.dp, AuthColor.checkboxBorder, RoundedCornerShape(3.dp))
                                    .background(if (state.isConsentChecked) AppColor.accent else Color.White)
                                    .clickable { viewModel.toggleConsent() },
                            contentAlignment = Alignment.Center,
                        ) {
                            if (state.isConsentChecked) {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(12.dp),
                                )
                            }
                        }

                        Text(
                            text = "Я согласен с Условиями предоставления услуг и Политикой конфиденциальности",
                            style = AppFont.weight.copy(fontSize = 12.sp),
                            color = AuthColor.secondaryText,
                        )
                    }
                }

                Button(
                    onClick = viewModel::submit,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AppColor.accent),
                    enabled = !state.isLoading,
                ) {
                    if (state.isLoading) {
                        Text("...", color = AppColor.textPrimary)
                    } else {
                        Text(
                            text = if (state.isRegisterMode) "Регистрация" else "Войти",
                            style = AppFont.sectionHeader,
                            color = AppColor.textPrimary,
                        )
                    }
                }

                state.error?.let { error ->
                    Text(
                        text = error,
                        color = AppColor.accent,
                        style = AppFont.weight.copy(fontSize = 12.sp),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }

                Text(
                    text = if (state.isRegisterMode) "Уже есть аккаунт?" else "У вас нет аккаунта?",
                    color = AuthColor.secondaryAction,
                    style = AppFont.weight.copy(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.toggleMode() },
                )
            }
        }
    }
}

@Composable
private fun AuthField(
    label: String,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    onTogglePassword: (() -> Unit)? = null,
) {
    Column {
        Text(
            text = label,
            style = AppFont.weight.copy(fontSize = 12.sp, fontWeight = FontWeight.Medium),
            color = AuthColor.label,
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    style = AppFont.weight.copy(fontSize = 13.sp),
                    color = AuthColor.placeholder,
                )
            },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(56.dp),
            shape = RoundedCornerShape(8.dp),
            colors =
                OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AuthColor.checkboxBorder,
                    unfocusedBorderColor = AuthColor.checkboxBorder,
                    focusedContainerColor = AuthColor.fieldBackground,
                    unfocusedContainerColor = AuthColor.fieldBackground,
                    cursorColor = AuthColor.fieldText,
                ),
            textStyle = AppFont.weight.copy(fontSize = 13.sp, color = AuthColor.fieldText),
            visualTransformation =
                if (isPassword && !isPasswordVisible) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                },
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = if (isPassword) KeyboardType.Password else keyboardType,
                    imeAction = ImeAction.Next,
                ),
            trailingIcon =
                if (isPassword && onTogglePassword != null) {
                    {
                        IconButton(onClick = onTogglePassword) {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = if (isPasswordVisible) "Скрыть пароль" else "Показать пароль",
                                tint = AuthColor.icon,
                            )
                        }
                    }
                } else {
                    null
                },
        )
    }
}
