package com.nexters.ziine.android.presentation.artworkdetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
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
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(Color.Transparent),
    ) {
//        IconButton(
//            onClick = onBackClick,
//            modifier = Modifier
//                .padding(top = 8.dp, start = 16.dp)
//                .then(Modifier.size(20.dp))
//        ) {
//            Icon(
//                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back),
//                contentDescription = "Arrow Back Icon",
//                tint = Color.Unspecified,
//            )
//        }
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back),
            contentDescription = "Arrow Back Icon",
            modifier = Modifier
                .size(20.dp)
                .padding(top = 8.dp, start = 16.dp)
                .clickable { onBackClick() },
            tint = Color.Unspecified,
        )
    }
}

@ComponentPreview
@Composable
private fun ArtworkDetailTopBarPreview() {
    ZiineTheme {
        ArtworkDetailTopBar(onBackClick = {})
    }
}
