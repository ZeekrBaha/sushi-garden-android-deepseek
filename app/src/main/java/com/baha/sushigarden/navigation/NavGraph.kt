package com.baha.sushigarden.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.baha.sushigarden.features.auth.AuthScreen
import com.baha.sushigarden.features.auth.AuthViewModel
import com.baha.sushigarden.features.cart.CartScreen
import com.baha.sushigarden.features.catalog.CatalogScreen
import com.baha.sushigarden.features.checkout.CheckoutScreen
import com.baha.sushigarden.features.orders.OrderDetailScreen
import com.baha.sushigarden.features.orders.OrdersScreen
import com.baha.sushigarden.features.productdetail.ProductDetailScreen
import com.baha.sushigarden.features.profile.ProfileScreen
import com.baha.sushigarden.features.promotions.PromotionsScreen
import com.baha.sushigarden.features.tracking.TrackingScreen
import com.baha.sushigarden.ui.designsystem.AppColor

sealed class Screen(
    val route: String,
) {
    data object Auth : Screen("auth")

    data object Catalog : Screen("catalog")

    data object Promotions : Screen("promotions")

    data object Orders : Screen("orders")

    data object Cart : Screen("cart")

    data object Profile : Screen("profile")

    data object ProductDetail : Screen("product_detail/{productId}") {
        fun createRoute(productId: String) = "product_detail/$productId"
    }

    data object Checkout : Screen("checkout")

    data object Tracking : Screen("tracking")

    data object OrderDetail : Screen("order_detail/{orderId}") {
        fun createRoute(orderId: String) = "order_detail/$orderId"
    }
}

data class TabItem(
    val screen: Screen,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

val tabs =
    listOf(
        TabItem(Screen.Catalog, "Каталог", Icons.Filled.GridView, Icons.Outlined.GridView),
        TabItem(Screen.Promotions, "Акции", Icons.Filled.Sell, Icons.Outlined.Sell),
        TabItem(Screen.Orders, "Заказы", Icons.Filled.Schedule, Icons.Outlined.Schedule),
        TabItem(Screen.Cart, "Корзина", Icons.Filled.AddShoppingCart, Icons.Outlined.AddShoppingCart),
        TabItem(Screen.Profile, "Профиль", Icons.Filled.Person, Icons.Outlined.Person),
    )

@Composable
fun SushiGardenNavHost() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()
    val isAuthenticated by authViewModel.isAuthenticated.collectAsState()

    if (!isAuthenticated) {
        NavHost(navController = navController, startDestination = Screen.Auth.route) {
            composable(Screen.Auth.route) {
                AuthScreen(
                    viewModel = authViewModel,
                    onAuthSuccess = { authViewModel.onAuthSuccess() },
                )
            }
        }
    } else {
        MainTabScreen(
            onNavigate = { screen -> navController.navigate(screen.route) },
            onNavigateBack = { navController.popBackStack() },
            onLogout = { authViewModel.signOut() },
        )
    }
}

@Composable
fun MainTabScreen(
    onNavigate: (Screen) -> Unit,
    onNavigateBack: () -> Unit,
    onLogout: () -> Unit = {},
) {
    val navController = rememberNavController()
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarVisible =
        tabs.any { tab ->
            currentDestination?.hierarchy?.any { it.route == tab.screen.route } == true
        }

    Scaffold(
        containerColor = AppColor.background,
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarVisible,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
            ) {
                NavigationBar(
                    containerColor = AppColor.tabBar,
                    contentColor = AppColor.textPrimary,
                    tonalElevation = 0.dp,
                ) {
                    tabs.forEachIndexed { index, tab ->
                        val selected = currentDestination?.hierarchy?.any { it.route == tab.screen.route } == true
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                selectedTab = index
                                navController.navigate(tab.screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (selected) tab.selectedIcon else tab.unselectedIcon,
                                    contentDescription = tab.label,
                                    tint = if (selected) AppColor.textPrimary else AppColor.inactive,
                                )
                            },
                            label = {
                                Text(
                                    text = tab.label,
                                    color = if (selected) AppColor.textPrimary else AppColor.inactive,
                                )
                            },
                            colors =
                                NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.Transparent,
                                ),
                        )
                    }
                }
            }
        },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Catalog.route,
            modifier = Modifier.padding(paddingValues),
        ) {
            composable(Screen.Catalog.route) {
                CatalogScreen(
                    onProductClick = { productId ->
                        navController.navigate(Screen.ProductDetail.createRoute(productId))
                    },
                )
            }
            composable(Screen.Promotions.route) { PromotionsScreen() }
            composable(Screen.Orders.route) {
                OrdersScreen(onOrderClick = { orderId ->
                    navController.navigate(Screen.OrderDetail.createRoute(orderId))
                })
            }
            composable(Screen.Cart.route) { CartScreen(onCheckout = { navController.navigate(Screen.Checkout.route) }) }
            composable(Screen.Profile.route) { ProfileScreen(onLogout = onLogout) }

            composable(
                route = Screen.ProductDetail.route,
                arguments = listOf(navArgument("productId") { type = NavType.StringType }),
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId") ?: ""
                ProductDetailScreen(productId = productId, onBack = { navController.popBackStack() })
            }

            composable(Screen.Checkout.route) {
                CheckoutScreen(onOrderPlaced = { navController.navigate(Screen.Tracking.route) })
            }

            composable(Screen.Tracking.route) { TrackingScreen() }

            composable(
                route = Screen.OrderDetail.route,
                arguments = listOf(navArgument("orderId") { type = NavType.StringType }),
            ) { backStackEntry ->
                val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
                OrderDetailScreen(orderId = orderId, onBack = { navController.popBackStack() })
            }
        }
    }
}
