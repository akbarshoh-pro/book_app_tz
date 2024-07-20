package com.example.book_app_tz.presentation.main

//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.rememberImagePainter
import com.example.book_app_tz.R
import com.example.book_app_tz.data.model.BookData
import com.example.book_app_tz.utils.ScreenStatus
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.orbitmvi.orbit.compose.collectAsState

class MainScreen : Screen {
    @SuppressLint("RememberReturnType")
    @Composable
    override fun Content() {
        val viewModel: MainContract.ViewModel = getViewModel<MainViewModel>()
        viewModel.onEventDispatcher(MainContract.Intent.LoadAllBooks)
        MainScreenContent(uiState = viewModel.collectAsState(), onEventDispatcher = viewModel::onEventDispatcher)
        val systemUiController = rememberSystemUiController()
        systemUiController.setSystemBarsColor(color = Color.White)
    }
}

@Composable
fun MainScreenContent(uiState: State<MainContract.UIState>, onEventDispatcher: (MainContract.Intent) -> Unit) {
    var time by remember { mutableLongStateOf(System.currentTimeMillis()) }

    when(uiState.value.screenStatus) {
        ScreenStatus.INITIAL -> {

        }
        ScreenStatus.LOADING -> LoadingState()
        ScreenStatus.SUCCESS -> SuccessState(books = uiState.value.books, itemClick = {
            if (System.currentTimeMillis() - time > 1000) {
                onEventDispatcher(MainContract.Intent.OpenDetail(it))
                time = System.currentTimeMillis()
            }
        })
        ScreenStatus.FAILURE -> FailureState(message = uiState.value.message)
    }
}

@Composable
fun LoadingState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.Blue
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SuccessState(books: List<BookData>, itemClick: (BookData) -> Unit) {
    Column(
        modifier = Modifier
            .padding(WindowInsets.statusBars.asPaddingValues())
            .background(Color(0xFFFFFFFF)),
    ) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 24.dp, top = 56.dp)
        ) {
            item {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    text = "Славянское фэнтези",
                    fontSize = 22.sp,
                    fontWeight = FontWeight(700)
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
            items(books.chunked(2).size) { pair ->
                val pairs = books.chunked(2)[pair]
                Row(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                ) {
                    BookItem(
                        modifier = Modifier
                            .weight(1F),
                        book = pairs[0],
                        onClick = itemClick
                    )
                    BookItem(
                        modifier = Modifier
                            .weight(1F),
                        book = pairs[1],
                        onClick = itemClick
                    )
                }
            }
        }
    }
}

@Composable
fun FailureState(message: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = message, fontSize = 18.sp, color = Color(0xFF000000))
    }
}

@Composable
fun BookItem(modifier: Modifier = Modifier, book: BookData, onClick: (BookData) -> Unit) {
    Column(
        modifier = modifier
            .padding(top = 10.dp, bottom = 10.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .clickable { onClick(book) },
    ) {
        Image(
            painter = rememberImagePainter(book.image ?: "https://netgalley-covers.s3.amazonaws.com/cover344479-medium.png"),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = book.title,
            fontSize = 18.sp,
            fontWeight = FontWeight(500),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = book.author,
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            maxLines = 1,
            color = Color(0xFF90A1AD),
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(16.dp),
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = book.rating,
                fontSize = 18.sp,
                fontWeight = FontWeight(500),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}