package com.nexters.ziine.android.presentation.artworkdetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nexters.ziine.android.presentation.R
import com.nexters.ziine.android.presentation.artworkdetail.model.UiArtistDetail
import com.nexters.ziine.android.presentation.artworkdetail.model.UiArtworkDetail
import com.nexters.ziine.android.presentation.artworkdetail.model.UiContact
import com.nexters.ziine.android.presentation.artworkdetail.model.UiExhibition
import com.nexters.ziine.android.presentation.artworkdetail.viewmodel.ArtworkDetailUiAction
import com.nexters.ziine.android.presentation.artworkdetail.viewmodel.ArtworkDetailUiState
import com.nexters.ziine.android.presentation.component.NetworkImage
import com.nexters.ziine.android.presentation.preview.ComponentPreview
import com.nexters.ziine.android.presentation.ui.theme.Paragraph2
import com.nexters.ziine.android.presentation.ui.theme.Subtitle1
import com.nexters.ziine.android.presentation.ui.theme.Subtitle3
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun ArtistDescription(
    uiState: ArtworkDetailUiState,
    onAction: (ArtworkDetailUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.background),
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = uiState.artwork.artist.name,
            style = Subtitle1,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NetworkImage(
                imageUrl = uiState.artwork.artist.profileImageUrl,
                contentDescription = "Profile Image of ${uiState.artwork.artist.name}",
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape),
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = uiState.artwork.artist.name,
                style = Paragraph2,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        EducationTags(educations = uiState.artwork.artist.education)
        Spacer(modifier = Modifier.height(40.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.artist_exhibition_record),
                style = Subtitle3,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        ExhibitionRecords(exhibitionRecords = uiState.artwork.artist.exhibition)
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.more_work_activities),
            style = Subtitle3,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(40.dp))
        ContactLinks(contacts = uiState.artwork.artist.contact)
    }
}

@ComponentPreview
@Composable
private fun ArtistDescriptionPreview() {
    ZiineTheme {
        ArtistDescription(
            uiState = ArtworkDetailUiState(
                artwork = UiArtworkDetail(
                    artist = UiArtistDetail(
                        id = 0,
                        name = "작가명",
                        profileImageUrl = "",
                        education = persistentListOf("이화여자대학교", "서양학과"),
                        exhibition = persistentListOf(
                            UiExhibition(
                                title = "서울 OO갤러리 개인전",
                                exhibitionDate = "2022.11.30",
                            ),
                            UiExhibition(
                                title = "서울 OO갤러리 개인전",
                                exhibitionDate = "2022.11.30",
                            ),
                            UiExhibition(
                                title = "서울 OO갤러리 개인전",
                                exhibitionDate = "2022.11.30",
                            ),
                        ),
                        contact = persistentListOf(
                            UiContact(
                                type = "INSTAGRAM",
                                value = "인스타그램 아이디",
                            ),
                            UiContact(
                                type = "WEBSITE",
                                value = "www.aaaabbbbcccc.com",
                            ),
                        )
                    )
                ),
            ),
            onAction = {},
        )
    }
}
