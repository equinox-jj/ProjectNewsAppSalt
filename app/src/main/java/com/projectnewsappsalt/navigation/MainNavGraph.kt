package com.projectnewsappsalt.navigation

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.projectnewsappsalt.core.destination.MainNavDestination
import com.projectnewsappsalt.core.utils.constants.Constants.ARTICLE_TITLE_ARGS
import com.projectnewsappsalt.core.utils.constants.Constants.URL_LINK_ARGS
import com.projectnewsappsalt.presentation.articledetail.ArticleDetail
import com.projectnewsappsalt.presentation.articles.ArticlesScreen
import com.projectnewsappsalt.presentation.articles.viewmodel.ArticlesEvent
import com.projectnewsappsalt.presentation.articles.viewmodel.ArticlesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = MainNavDestination.Articles.route
    ) {
        composable(route = MainNavDestination.Articles.route) {
            val viewModel: ArticlesViewModel = koinViewModel()
            val state by viewModel.uiState.collectAsState()
            val snackBarHostState = remember { SnackbarHostState() }

            LaunchedEffect(key1 = viewModel.uiEvent, block = {
                viewModel.uiEvent.collect { articleEvent ->
                    when (articleEvent) {
                        is ArticlesEvent.ToastArticleErrorMessage -> {
                            val snackBarResult = snackBarHostState.showSnackbar(
                                message = "An unknown error occurred.",
                                actionLabel = "Tap to Refresh",
                                withDismissAction = false,
                                duration = SnackbarDuration.Indefinite,
                            )

                            when (snackBarResult) {
                                SnackbarResult.Dismissed -> {}
                                SnackbarResult.ActionPerformed -> {
                                    viewModel.onEvent(ArticlesEvent.OnGetArticleList())
                                }
                            }
                        }

                        else -> {}
                    }
                }
            })

            ArticlesScreen(
                state = state,
                event = viewModel::onEvent,
                snackBarHostState = snackBarHostState,
                navigateToDetail = { urlLink, articleTitle ->
                    navController.navigate(
                        MainNavDestination.ArticleDetail.passUrlAndArticleTitle(
                            url = urlLink,
                            articleTitle = articleTitle,
                        )
                    )
                }
            )
        }

        composable(route = MainNavDestination.ArticleDetail.route, arguments = listOf(
            navArgument(name = URL_LINK_ARGS) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(name = ARTICLE_TITLE_ARGS) {
                type = NavType.StringType
                nullable = true
            }
        )) {
            val urlLink = it.arguments?.getString(URL_LINK_ARGS)
            val articleTitle = it.arguments?.getString(ARTICLE_TITLE_ARGS)

            ArticleDetail(
                urlLink = urlLink.orEmpty(),
                articleTitle = articleTitle.orEmpty(),
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}