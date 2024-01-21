package ir.fallahpoor.cleankmmdemo.rocketlaunches

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ir.fallahpoor.cleankmmdemo.R
import ir.fallahpoor.cleankmmdemo.domain.model.RocketLaunch
import ir.fallahpoor.cleankmmdemo.theme.CleanKmmDemoTheme
import ir.fallahpoor.cleankmmdemo.theme.Space

@Composable
fun RocketLaunchItem(modifier: Modifier = Modifier, rocketLaunch: RocketLaunch) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(Space.NORMAL)
    ) {
        Column {
            LaunchImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(topEnd = Space.NORMAL, topStart = Space.NORMAL)),
                imageUrl = rocketLaunch.imageUrl
            )
            Column(modifier = Modifier.padding(Space.NORMAL)) {
                LaunchName(
                    modifier = Modifier.fillMaxWidth(),
                    name = rocketLaunch.name
                )
                LaunchDescription(
                    modifier = Modifier.fillMaxWidth(),
                    description = rocketLaunch.description
                )
                LaunchResult(
                    modifier = Modifier.fillMaxWidth(),
                    successful = rocketLaunch.successful
                )
            }
        }
    }
}

@Composable
private fun LaunchImage(modifier: Modifier = Modifier, imageUrl: String?) {
    if (imageUrl != null) {
        AsyncImage(
            modifier = modifier,
            contentScale = ContentScale.Crop,
            model = imageUrl,
            contentDescription = null
        )
    }
}

@Composable
private fun LaunchName(modifier: Modifier = Modifier, name: String) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier.padding(end = Space.SMALL),
            text = stringResource(R.string.name),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun LaunchDescription(modifier: Modifier = Modifier, description: String?) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier.padding(end = Space.SMALL),
            text = stringResource(R.string.description),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description ?: stringResource(R.string.not_available),
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun LaunchResult(modifier: Modifier = Modifier, successful: Boolean?) {
    val text = if (successful != null) {
        if (successful) {
            stringResource(R.string.yes)
        } else {
            stringResource(R.string.no)
        }
    } else {
        stringResource(R.string.not_available)
    }
    Row(modifier = modifier) {
        Text(
            modifier = Modifier.padding(end = Space.SMALL),
            text = stringResource(R.string.successful),
            fontWeight = FontWeight.Bold
        )
        Text(text = text)
    }
}

@Preview
@Composable
private fun RocketLaunchItemPreview() {
    CleanKmmDemoTheme {
        RocketLaunchItem(
            rocketLaunch = RocketLaunch(
                id = "1",
                name = "Launch name",
                flightNumber = 1,
                description = "Here is some sample description",
                successful = true,
                imageUrl = "Some image url here"
            )
        )
    }
}

@Preview(uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RocketLaunchItemPreviewDark() {
    CleanKmmDemoTheme {
        RocketLaunchItem(
            rocketLaunch = RocketLaunch(
                id = "1",
                name = "Launch name",
                flightNumber = 1,
                description = "Here is some sample description",
                successful = true,
                imageUrl = "Some image url here"
            )
        )
    }
}