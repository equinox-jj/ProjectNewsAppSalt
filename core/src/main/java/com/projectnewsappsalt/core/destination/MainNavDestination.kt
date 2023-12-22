package com.projectnewsappsalt.core.destination

import com.projectnewsappsalt.core.utils.constants.Constants.ARTICLE_TITLE_ARGS
import com.projectnewsappsalt.core.utils.constants.Constants.URL_LINK_ARGS

sealed class MainNavDestination(val route: String) {
    data object Articles : MainNavDestination(route = "articles")
    data object ArticleDetail :
        MainNavDestination(route = "article_detail?urlLink={$URL_LINK_ARGS},articleTitle={$ARTICLE_TITLE_ARGS}") {
        fun passUrlAndArticleTitle(url: String, articleTitle: String) =
            "article_detail?urlLink=$url,articleTitle=$articleTitle"
    }
}