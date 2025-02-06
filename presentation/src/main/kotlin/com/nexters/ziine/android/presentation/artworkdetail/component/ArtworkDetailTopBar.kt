package com.nexters.ziine.android.presentation.artworkdetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.nexters.ziine.android.presentation.R
import com.nexters.ziine.android.presentation.preview.ComponentPreview
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
internal fun ArtworkDetailTopBar(
    onBackClick: () -> Unit,
    isScrolling: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(44.dp)
                .background(if (isScrolling) MaterialTheme.colorScheme.background else Color.Transparent,),
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .padding(top = 8.dp, start = 16.dp)
                    .then(Modifier.size(20.dp))
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back),
                    contentDescription = "Arrow Back Icon",
                    tint = Color.Unspecified,
                )
            }
        }
        if (isScrolling) {
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outline,
            )
        }
    }
}

@ComponentPreview
@Composable
private fun ArtworkDetailTopBarPreview() {
    ZiineTheme {
        ArtworkDetailTopBar(
            isScrolling = true,
            onBackClick = {},
        )
    }
}
