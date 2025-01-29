package com.nexters.ziine.android.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nexters.ziine.android.R

val pretendardFamily = FontFamily(
    Font(R.font.pretendard_semi_bold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.pretendard_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.pretendard_regular, FontWeight.Normal, FontStyle.Normal),
)

val Heading3 = TextStyle(
    fontFamily = pretendardFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 28.sp,
    lineHeight = 36.sp,
)

val Heading4 = TextStyle(
    fontFamily = pretendardFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 24.sp,
    lineHeight = 31.sp,
)

val Heading5 = TextStyle(
    fontFamily = pretendardFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 20.sp,
    lineHeight = 26.sp,
)

val Subtitle1 = TextStyle(
    fontFamily = pretendardFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 18.sp,
    lineHeight = 23.sp,
)

val Subtitle2 = TextStyle(
    fontFamily = pretendardFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    lineHeight = 21.sp,
)

val Subtitle3 = TextStyle(
    fontFamily = pretendardFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    lineHeight = 18.sp,
)

val Paragraph1 = TextStyle(
    fontFamily = pretendardFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    lineHeight = 26.sp,
)

val Paragraph2 = TextStyle(
    fontFamily = pretendardFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = 22.sp,
)

val Paragraph3 = TextStyle(
    fontFamily = pretendardFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 13.sp,
    lineHeight = 21.sp,
)

val Paragraph4 = TextStyle(
    fontFamily = pretendardFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    lineHeight = 19.sp,
)

val Paragraph5 = TextStyle(
    fontFamily = pretendardFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 11.sp,
    lineHeight = 18.sp,
)

val Paragraph6 = TextStyle(
    fontFamily = pretendardFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 10.sp,
    lineHeight = 16.sp,
)
