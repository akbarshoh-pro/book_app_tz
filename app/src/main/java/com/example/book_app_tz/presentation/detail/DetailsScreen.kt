package com.example.book_app_tz.presentation.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.rememberImagePainter
import com.example.book_app_tz.R
import com.example.book_app_tz.data.model.BookData
import com.example.book_app_tz.presentation.main.MainContract
import com.example.book_app_tz.presentation.main.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun DetailsScreen(navController: NavController, index: Int) {

    val viewModel: DetailsViewModel = hiltViewModel()
    viewModel.onEventDispatcher(DetailsContract.Intent.LoadDada(index))

    DetailsScreenContent(navController = navController, viewModel = viewModel)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreenContent(navController: NavController, viewModel: DetailsContract.ViewModel) {
    val uiState by viewModel.collectAsState()
    val book = uiState.data
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = Color(0xFFE43C22))

    viewModel.collectSideEffect { sideEffect ->
        when(sideEffect) {
            is DetailsContract.SideEffect.BackMain -> {
                navController.popBackStack()
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4.3F)
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFFE43C22), Color(0xFFC00D0F)),
                        ),
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(WindowInsets.statusBars.asPaddingValues())
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(shape = RoundedCornerShape(100))
                                .background(Color(0xFFFFFFFF))
                                .clickable { viewModel.onEventDispatcher(DetailsContract.Intent.BackMain) },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(28.dp),
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = null,
                                tint = Color(0xFFE43C22)
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(shape = RoundedCornerShape(100))
                                .background(Color(0xFFFFFFFF)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(28.dp),
                                painter = painterResource(id = R.drawable.ic_bookmark),
                                contentDescription = null,
                                tint = Color(0xFF2B2B2B)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(shape = RoundedCornerShape(100))
                                .background(Color(0xFFFFFFFF)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(28.dp),
                                painter = painterResource(id = R.drawable.ic_share),
                                contentDescription = null,
                                tint = Color(0xFF2B2B2B)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(28.dp))
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    ) {
                        Image(
                            painter = rememberImagePainter(book.image ?: "https://netgalley-covers.s3.amazonaws.com/cover344479-medium.png"),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(200.dp)
                                .width(140.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(
                            modifier = Modifier
                                .height(200.dp)
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = book.title,
                                fontSize = 26.sp,
                                maxLines = 2,
                                fontWeight = FontWeight(500),
                                color = Color(0xFFFFFFFF),
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(18.dp))
                            Column{
                                Row {
                                    Text(
                                        text = "Автор: ",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFFFFFFFF),
                                    )
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = book.author,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(500),
                                        maxLines = 1,
                                        color = Color(0xFFFFFFFF),
                                        overflow = TextOverflow.Ellipsis,
                                        textDecoration = TextDecoration.Underline
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                Row {
                                    Text(
                                        text = "Рейтинг: ",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(500),
                                        maxLines = 1,
                                        color = Color(0xFFFFFFFF),
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            modifier = Modifier
                                                .size(16.dp),
                                            painter = painterResource(id = R.drawable.ic_star),
                                            contentDescription = null
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            modifier = Modifier.fillMaxWidth(),
                                            text = book.rating,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight(500),
                                            maxLines = 1,
                                            color = Color(0xFFFFFFFF),
                                            overflow = TextOverflow.Ellipsis,
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                Row {
                                    Text(
                                        text = "Категория: ",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFFFFFFFF),
                                    )
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = book.section,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(500),
                                        maxLines = 1,
                                        color = Color(0xFFFFFFFF),
                                        overflow = TextOverflow.Ellipsis,
                                        textDecoration = TextDecoration.Underline
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Скачиваний: ${book.downloads}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(500),
                                    maxLines = 1,
                                    color = Color(0xFFFFFFFF),
                                    overflow = TextOverflow.Ellipsis,
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Отзывы: ${book.comments}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(500),
                                    maxLines = 1,
                                    color = Color(0xFFFFFFFF),
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(6F)
                    .background(Color(0xFFFFFFFF)),
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(24))
                        .background(Color(0xFFE43C22)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(id = R.drawable.ic_open_book),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Читать онлайн",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    text = "О книге",
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = book.summary.trimIndent().replace("<p>","",).replace("</p>",""),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
