package com.nexters.ziine.android.presentation.artworkdetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nexters.ziine.android.presentation.preview.ComponentPreview
import com.nexters.ziine.android.presentation.ui.theme.Paragraph4
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun EducationTags(educations: ImmutableList<String>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(educations) { material ->
            EducationTag(text = material)
        }
    }
}

@Composable
private fun EducationTag(text: String) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .height(27.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "#$text",
            modifier = Modifier.padding(horizontal = 10.dp),
            color = MaterialTheme.colorScheme.onTertiary,
            style = Paragraph4,
        )
    }
}

@ComponentPreview
@Composable
private fun EducationTagsPreview() {
    ZiineTheme {
        EducationTags(persistentListOf("이화여자대학교", "서양학과"))
    }
}
