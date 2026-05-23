package com.baha.sushigarden.ui.designsystem

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.baha.sushigarden.R

val SenFontFamily =
    FontFamily(
        Font(R.font.sen_bold, FontWeight.Bold),
        Font(R.font.sen_regular, FontWeight.Normal),
    )

object AppFont {
    val price =
        TextStyle(
            fontFamily = SenFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 19.3.sp,
        )
    val productTitle =
        TextStyle(
            fontFamily = SenFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.6.sp,
        )
    val sectionHeader =
        TextStyle(
            fontFamily = SenFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 15.8.sp,
        )
    val weight =
        TextStyle(
            fontFamily = SenFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.2.sp,
        )
    val tabLabel =
        TextStyle(
            fontFamily = SenFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 11.8.sp,
        )
}
