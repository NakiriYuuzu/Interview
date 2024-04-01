package net.yuuzu.test.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import net.yuuzu.test.ui.theme.TestTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadData()
        setContent {
            TestTheme {
                MainView(
                    state = viewModel.state
                )
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
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()
        ) {
            Text(
                text = "Total Count: $totalCount",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }
        LazyColumn {
            items(items.size) {
                ItemComponent(
                    name = items[it].user.nickName,
                    tags = items[it].tags
                )
            }
        }
    }
}

@Composable
fun ItemComponent(
    name: String,
    tags: List<String>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Column {
            NameTag(name = name)
            ItemTag(tags = tags)
        }
    }
}

@Composable
fun NameTag(
    name: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "User Image")
            Text(text = name, fontSize = 16.sp)
        }
    }
}

@Composable
fun ItemTag(
    tags: List<String>,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row {
            tags.forEach {
                Text(text = it, fontSize = 16.sp)
            }
        }
    }
}