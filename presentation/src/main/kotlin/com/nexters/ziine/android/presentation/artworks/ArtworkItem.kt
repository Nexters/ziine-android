package com.nexters.ziine.android.presentation.artworks

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nexters.ziine.android.presentation.R
import com.nexters.ziine.android.presentation.artworks.model.Artwork
import com.nexters.ziine.android.presentation.component.NetworkImage
import com.nexters.ziine.android.presentation.preview.ComponentPreview
import com.nexters.ziine.android.presentation.ui.theme.Gray0
import com.nexters.ziine.android.presentation.ui.theme.Heading4
import com.nexters.ziine.android.presentation.ui.theme.Paragraph2
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

@Composable
fun ArtworkItem(
    artwork: Artwork,
    onArtworkItemSelect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                onClick = onArtworkItemSelect,
            ),
    ) {
        NetworkImage(
            imageUrl = artwork.imageUrl,
            contentDescription = "Artwork by ${artwork.artistName}",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(
                    ratio = LocalDensity.current.run {
                        // 이미지 로드 후 실제 비율을 계산하여 적용
                        remember(artwork.imageUrl) { 1f } // 기본값으로 1:1 비율 설정
                    },
                ),
            contentScale = ContentScale.FillWidth,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp)
                .align(Alignment.TopStart),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = "Profile picture of ${artwork.artistName}",
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = artwork.artistName,
                style = Paragraph2,
                color = Gray0,
                modifier = Modifier.weight(1f),
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                .align(Alignment.BottomStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = artwork.title,
                color = Gray0,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = Heading4,
            )
        }
    }
}

@ComponentPreview
@Composable
private fun ArtworkItemPreview() {
    ZiineTheme {
        ArtworkItem(
            artwork = Artwork(
                id = 1,
                imageUrl = "https://example.com/artwork.jpg",
                artistName = "Artist Name",
                title = "Artwork Name",
            ),
            onArtworkItemSelect = {},
        )
    }
}
