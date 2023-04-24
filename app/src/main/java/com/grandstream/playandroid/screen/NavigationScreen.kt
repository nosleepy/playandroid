package com.grandstream.playandroid.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController = navController) }
        composable("web?url={url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            WebScreen(navController = navController, url = url)
        }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("collect") { CollectScreen(navController) }
        composable("share") { ShareScreen(navController) }
        composable("search") { SearchScreen(navController) }
        composable("system") { SystemScreen(navController) }
        composable("tool") { ToolScreen(navController) }
        composable("search_result?keyword={keyword}") { backStackEntry ->
            val keyword = backStackEntry.arguments?.getString("keyword") ?: ""
            SearchResultScreen(navController = navController, keyword = keyword)
        }
        composable("chapter?id={id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: "0"
            ChapterScreen(navController = navController, id = id.toLong())
        }
        composable("article_list?id={id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: "0"
            ArticleListScreen(navController = navController, id = id.toLong())
        }
    }
}