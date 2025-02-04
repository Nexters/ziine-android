package com.nexters.ziine.android.presentation.artworkdetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.nexters.ziine.android.presentation.R
import com.nexters.ziine.android.presentation.artworkdetail.model.UiContact
import com.nexters.ziine.android.presentation.preview.ComponentPreview
import com.nexters.ziine.android.presentation.ui.theme.Paragraph4
import com.nexters.ziine.android.presentation.ui.theme.Paragraph5
import com.nexters.ziine.android.presentation.ui.theme.ZiineTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun ContactLinks(contacts: ImmutableList<UiContact>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (contacts.isEmpty()) 0.dp else ((contacts.size * 44).dp)),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(contacts) { contact ->
            ContactLink(contact = contact)
        }
    }
}

@Composable
private fun ContactLink(contact: UiContact) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(27.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (contact.type == stringResource(R.string.instagram)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_instagram),
                contentDescription = "Contact Icon",
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified,
            )
        } else {
            Icon(
                ImageVector.vectorResource(R.drawable.ic_contact),
                contentDescription = "Contact Icon",
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified,
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "#${contact.value}",
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            style = Paragraph4,
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .height(27.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .clickable {},
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(R.string.copy),
                modifier = Modifier.padding(horizontal = 10.dp),
                color = MaterialTheme.colorScheme.onTertiary,
                style = Paragraph5,
            )
        }
    }
}

@ComponentPreview
@Composable
private fun ContactsPreview() {
    ZiineTheme {
        ContactLinks(
            persistentListOf(
                UiContact(type = stringResource(R.string.instagram), value = "y_joo_z"),
                UiContact(type = stringResource(R.string.website), value = "www.aaaabbbbcccc.com"),
            ),
        )
    }
}
