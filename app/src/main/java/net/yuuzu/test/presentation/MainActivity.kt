package net.yuuzu.test.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import net.yuuzu.test.data.User
import net.yuuzu.test.ui.theme.TestTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadData()
        setContent {
            TestTheme {
                MainView(state = viewModel.state)
            }
        }
    }
}

@Composable
fun MainView(
    state: MainState
) {
    val totalCount = state.data?.data?.totalCount ?: 0
    val items = state.data?.data?.items ?: emptyList()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0EAE2))
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "totalCount: $totalCount",
                color = Color.Black,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        items(items.size) { index ->
            ProfileCard(
                user = items[index].user,
                items = items[index].tags
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileCard(
    user: User,
    items: List<String>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF1EE))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            NameTag(text = user.nickName, imageUrl = user.imageUrl)

            Spacer(modifier = Modifier.height(16.dp))

            FlowRow(
                maxItemsInEachRow = 5
            ) {
                items.forEach { tag ->
                    ItemTag(text = tag)
                }
            }
        }
    }
}

@Composable
fun ItemTag(text: String) {
    Surface(
        modifier = Modifier.wrapContentSize(),
        shape = RoundedCornerShape(30.dp),
        color = Color(0xFFFFDAD4)
    ) {
        Text(
            text = text,
            color = Color(0xFFD87C49),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun NameTag(
    text: String,
    imageUrl: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Profile picture",
            modifier = Modifier.size(40.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge
        )
    }
}