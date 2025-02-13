package com.nexters.ziine.android.presentation.component

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.nexters.ziine.android.presentation.R
import com.nexters.ziine.android.presentation.preview.ComponentPreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
fun RegisterTopBar(
    modifier: Modifier = Modifier,
    isScrolled: Boolean,
    onBackButtonClickedAction: () -> Unit,
) {
    val backGroundColor: Color =
        if (isScrolled) MaterialTheme.colorScheme.background else Color.Transparent
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backGroundColor),
    ) {
        Row(
            modifier = Modifier.height(44.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(
                onClick = onBackButtonClickedAction,
                modifier = Modifier.size(20.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back),
                    contentDescription = "백버튼 아이콘",
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
        if (isScrolled) {
            HorizontalDivider(
                color = MaterialTheme.colorScheme.outline,
                thickness = 1.dp,
            )
        }
    }
}

@ComponentPreview
@Composable
private fun RegisterTopBarPreview() {
    ZiineTheme {
        RegisterTopBar(isScrolled = true) { }
    }
}
