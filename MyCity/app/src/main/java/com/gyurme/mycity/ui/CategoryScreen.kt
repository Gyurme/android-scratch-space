package com.gyurme.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.gyurme.mycity.R
import com.gyurme.mycity.data.Category
import com.gyurme.mycity.data.local.LocalRecommendationDataProvider
import com.gyurme.mycity.ui.theme.MyCityTheme

@Composable
fun CategoryScreen(
    categories: List<Category>,
    onCategoryCardClicked: (Category) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = WindowInsets.safeDrawing.asPaddingValues(),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.category_list_item_vertical_spacing)
        )
    ) {
        items(categories, key = { category -> category.ordinal })
        { category ->
            CategoryListItem(
                category = category,
                selected = false,
                onCardClick = {
                     onCategoryCardClicked(category)
                }
            )
        }
    }
}

@Composable
fun CategoryListItem(
    category: Category,
    selected: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.secondaryContainer
        ),
        onClick = onCardClick
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {

            Image(
                painterResource(id = category.image),
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
            )

            Text(
                text = category.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding( vertical = dimensionResource(R.dimen.padding_small),
                    horizontal = dimensionResource(R.dimen.padding_medium))
            )

        }
    }
}

@Preview
@Composable
fun CategoryScreenPreview() {
    MyCityTheme {
        CategoryScreen(
            categories = LocalRecommendationDataProvider.allRecommendations.map { it.category },
            onCategoryCardClicked = { },
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}
