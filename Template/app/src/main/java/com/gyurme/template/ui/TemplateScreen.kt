package com.gyurme.template.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gyurme.template.R
import com.gyurme.template.data.Transaction
import com.gyurme.template.data.TransactionStatus
import java.text.NumberFormat
import java.util.Locale // Required for NumberFormat

@Composable
fun TemplateScreen(
    templateUiState: TemplateUiState,
    onApproveTransaction: (Long) -> Unit,
    retryAction: () -> Unit = {},
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (templateUiState) {
        is TemplateUiState.Loading ->
            LoadingScreen(modifier)

        is TemplateUiState.Success ->
            HomeScreen(
                transactions = templateUiState.transactions,
                onApproveTransaction = onApproveTransaction,
                modifier = modifier,
                contentPadding
            )

        is TemplateUiState.Error ->
            ErrorScreen(retryAction, modifier)
    }
}

@Composable
fun HomeScreen(
    transactions: List<Transaction>,
    onApproveTransaction: (Long) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(12.dp) // Increased spacing
    ) {
        items(transactions) { transaction ->
            TransactionItem(
                transaction = transaction,
                onApproveClicked = { onApproveTransaction(transaction.id) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun TransactionItem(
    transaction: Transaction,
    onApproveClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp), // Slightly more rounded
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = transaction.merchant,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                // Format amount as currency
                val formattedAmount =
                    NumberFormat.getCurrencyInstance(Locale.getDefault()).format(transaction.amount)
                Text(
                    text = formattedAmount,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (transaction.amount >= 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Date: ${transaction.date}", // Consider formatting this date too
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (transaction.description != null && transaction.description.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = transaction.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Category: ${transaction.category}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                val statusColor = when (transaction.status) {
                    TransactionStatus.APPROVED -> Color(0xFF388E3C)
                    TransactionStatus.PENDING -> Color(0xFFF57C00)
                    TransactionStatus.DECLINED -> MaterialTheme.colorScheme.error
                }
                Text(
                    text = transaction.status.name.lowercase(Locale.ROOT)
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                    style = MaterialTheme.typography.labelMedium,
                    color = statusColor,
                    fontWeight = FontWeight.Bold
                )
            }

            // Add Approve Button if status is PENDING
            if (transaction.status == TransactionStatus.PENDING) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onApproveClicked,
                    modifier = Modifier.align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C)) // Green button
                ) {
                    Text("Approve")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionItemPreview() {
    val sampleTransaction = Transaction(
        id = 1,
        amount = 100.00,
        merchant = "Uber Eats",
        date = "2023-10-26",
        status = TransactionStatus.APPROVED,
        category = "Food",
        description = "Dinner with friends"
    )
    MaterialTheme { // Wrap preview in MaterialTheme for styles to apply
        TransactionItem(
            transaction = sampleTransaction, modifier = Modifier.width(380.dp),
            onApproveClicked = {  }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionItemPreviewDeclined() {
    val sampleTransaction = Transaction(
        id = 2,
        amount = -25.50, // Negative amount example
        merchant = "Spotify",
        date = "2023-10-27",
        status = TransactionStatus.DECLINED,
        category = "Subscription",
        description = "Monthly fee"
    )
    MaterialTheme {
        TransactionItem(
            transaction = sampleTransaction, modifier = Modifier.width(380.dp),
            onApproveClicked = {  }
        )
    }
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
            modifier = Modifier, // Consider adding size here
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}
