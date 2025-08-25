package com.gyurme.template.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gyurme.template.R
import com.gyurme.template.data.Transaction
import com.gyurme.template.data.TransactionStatus

@Composable
fun TemplateScreen(
    templateUiState: TemplateUiState,
    retryAction: () -> Unit = {},
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (templateUiState) {
        is TemplateUiState.Loading ->
            LoadingScreen(modifier)

        is TemplateUiState.Success ->
            HomeScreen(
                templateUiState.transactions,
                modifier,
                contentPadding
            )

        is TemplateUiState.Error ->
            ErrorScreen(retryAction, modifier)
    }
}

@Composable
fun HomeScreen(
    transactions: List<Transaction>, modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(transactions) { transaction ->
            TransactionItem(transaction = transaction, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction, modifier: Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Transaction ID: ${transaction.id}")
            Text(text = "Amount: ${transaction.amount}")
            Text(text = "Date: ${transaction.date}")
            Text(text = "Description: ${transaction.description}")
            Text(text = "Category: ${transaction.category}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionItemPreview() {
    val sampleTransaction = Transaction(id = 1, amount = 100.00, merchant = "Uber", date = "2023-10-26", status = TransactionStatus.APPROVED, category = "Groceries", "Food")
    TransactionItem(transaction = sampleTransaction, modifier = Modifier.width(300.dp))
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier,
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}