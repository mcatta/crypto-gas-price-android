package dev.marcocattaneo.cryptogasprice.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import dev.marcocattaneo.cryptogasprice.ui.ScreenRoutes

@Composable
fun BottomBar(
    navController: NavHostController,
    items: List<ScreenRoutes>
) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
        items.forEach { screen ->
            val label = stringResource(id = screen.resourceId)
            BottomNavigationItem(
                icon = { Icon(screen.icon, label) },
                label = { Text(label) },
                selected = currentRoute == screen.route,
                onClick = {
                    // This if check gives us a "singleTop" behavior where we do not create a
                    // second instance of the composable if we are already on that destination
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}


@Composable
fun PrimaryButton(label: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(
            text = label,
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
fun PrimaryButton(icon: ImageVector, desc: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Icon(imageVector = icon, contentDescription = desc)
    }
}


@Composable
@Preview
fun Loading() {
    Text(
        text = "Loading...",
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Center
    )
}