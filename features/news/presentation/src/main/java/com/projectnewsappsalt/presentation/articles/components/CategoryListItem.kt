package com.projectnewsappsalt.presentation.articles.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CategoryListItem(
    categoryName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    chipShape: Shape = RoundedCornerShape(10.dp),
    chipElevation: Dp = 3.dp,
    isChipSelected: Boolean = false,
) {
    ElevatedFilterChip(
        selected = isChipSelected,
        onClick = onClick,
        label = { Text(text = categoryName.replaceFirstChar { it.uppercase() }) },
        modifier = modifier,
        shape = chipShape,
        elevation = FilterChipDefaults.filterChipElevation(elevation = chipElevation),
    )
}