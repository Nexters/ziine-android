package com.nexters.ziine.android.presentation.magazine

import android.graphics.Color.parseColor
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nexters.ziine.android.presentation.component.NetworkImage
import com.nexters.ziine.android.presentation.magazine.model.UiMagazine
import com.nexters.ziine.android.presentation.preview.ComponentPreview
import com.nexters.ziine.android.presentation.ui.theme.Heading3
import com.nexters.ziine.android.presentation.ui.theme.Paragraph2
import com.nexters.ziine.android.presentation.ui.theme.Paragraph4
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme

const val MAGAZINE_ITEM_WIDTH = 288
const val MAGAZINE_ITEM_HEIGHT = 479

@Composable
fun MagazineItem(data: UiMagazine, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .width(MAGAZINE_ITEM_WIDTH.dp)
            .height(MAGAZINE_ITEM_HEIGHT.dp),
        colors = CardDefaults.cardColors(containerColor = Color(parseColor(data.backgroundColor))),
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                text = data.title,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = Heading3,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = data.subTitle,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = Paragraph2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.weight(1f))
            MagazineTags(data.keyWords)
            Spacer(modifier = Modifier.height(16.dp))
            NetworkImage(
                imageUrl = data.thumbnailUrl,
                contentDescription = "매거진 썸네일 이미지",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f / 1f)
                    .clip(RoundedCornerShape(16.dp)),
            )
        }
    }
}

@Composable
private fun MagazineTags(tags: List<String>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        tags.forEach { MagazineTag(it) }
    }
}

@Composable
private fun MagazineTag(tagName: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .height(27.dp)
            .border(1.5.dp, MaterialTheme.colorScheme.background, RoundedCornerShape(99.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
            .wrapContentSize(Alignment.Center),
        text = "#$tagName",
        color = MaterialTheme.colorScheme.background,
        style = Paragraph4,
    )
}

@ComponentPreview
@Composable
private fun MagazineTagPreview() {
    ZiineTheme {
        MagazineTag("테스트")
    }
}
