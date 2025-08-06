package app.sahhamarket.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import app.sahhamarket.compose.theme.AthensGray
import app.sahhamarket.compose.theme.BaliHai
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.resources.R

@Composable
fun SearchBarContent(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    var textContent by remember { mutableStateOf("") }

    TextField(
        value = textContent,
        onValueChange = {
            textContent = it
            if (it.length > 2) {
                onSearch(it)
            }
        },
        textStyle = MaterialTheme.typography.titleMedium,
        placeholder = {
            Text(text = stringResource(R.string.txt_hint_search))
        },
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                contentDescription = null
            )
        },
        trailingIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.ms),
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.ms)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_photo_capture),
                    contentDescription = null,
                    modifier = Modifier.clickable { }
                )

                VerticalDivider(
                    thickness = MaterialTheme.spacing.xxxs,
                    color = Black,
                )

                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_microphone),
                    contentDescription = null,
                    modifier = Modifier.clickable { }
                )
            }
        },
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedLeadingIconColor = BaliHai,
            focusedLeadingIconColor = Black,
            unfocusedTrailingIconColor = FountainBlue,
            focusedTrailingIconColor = FountainBlue,
            unfocusedContainerColor = AthensGray,
            focusedContainerColor = AthensGray,
            unfocusedPlaceholderColor = BaliHai,
            focusedPlaceholderColor = Black,
            focusedTextColor = Black,
            unfocusedTextColor = Black,
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.s)
    )
}