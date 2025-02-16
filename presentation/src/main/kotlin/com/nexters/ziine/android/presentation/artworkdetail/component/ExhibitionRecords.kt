package com.nexters.ziine.android.presentation.artworkdetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nexters.ziine.android.presentation.artworkdetail.model.UiExhibition
import com.nexters.ziine.android.presentation.preview.ComponentPreview
import com.nexters.ziine.android.presentation.ui.theme.Paragraph4
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun ExhibitionRecords(exhibitionRecords: ImmutableList<UiExhibition>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (exhibitionRecords.isEmpty()) 0.dp else ((exhibitionRecords.size * 72).dp)),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(exhibitionRecords) { exhibitionRecord ->
            ExhibitionRecord(exhibitionRecord = exhibitionRecord)
        }
    }
}

@Composable
private fun ExhibitionRecord(exhibitionRecord: UiExhibition) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.primaryContainer),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = exhibitionRecord.exhibitionDate,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = Paragraph4,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = exhibitionRecord.title,
                color = MaterialTheme.colorScheme.onBackground,
                style = Paragraph4,
            )
        }
    }
}

@ComponentPreview
@Composable
private fun EducationTagsPreview() {
    ZiineTheme {
        EducationTags(persistentListOf("이화여자대학교", "서양학과"))
    }
}
