package com.projectnewsappsalt.presentation.articles.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.projectnewsappsalt.core.utils.helper.formatDate
import com.projectnewsappsalt.domain.entities.ArticleEntity
import com.projectnewsappsalt.presentation.R

@Stable
@Composable
fun ArticleCardItem(
    data: ArticleEntity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(20.dp),
    elevation: CardElevation = CardDefaults.elevatedCardElevation(defaultElevation = 3.dp),
) {
    val context = LocalContext.current
    ElevatedCard(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        elevation = elevation,
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(data.urlToImage)
                    .crossfade(true)
                    .build(),
                contentDescription = "Article Image",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
                    .clip(shape = RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder),
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = data.title,
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 5,
                )
                Text(
                    text = formatDate(data.publishedAt),
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Thin,
                        textAlign = TextAlign.End,
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }
    }
}