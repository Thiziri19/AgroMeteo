package com.example.agrometeo.ui.Home

import android.graphics.drawable.Icon
import android.net.wifi.hotspot2.pps.HomeSp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.agrometeo.navigation.HomeContent

@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               currentHomeTab: HomeContent,
               onSelectHomeTab: (HomeContent) -> Unit,
               content: @Composable () -> Unit) {


    val title = when (currentHomeTab) {
        HomeContent.CULTURES -> "Toutes les cultures"
        HomeContent.MAP -> "Consulter la carte"
        HomeContent.FORCASTS -> "Prévisions météorologiques"
        HomeContent.PROFILE -> "Mon profil"
    }
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Row(
                modifier = modifier.fillMaxWidth().statusBarsPadding().padding(horizontal = 24.dp, vertical = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Surface(
                    modifier = modifier.size(48.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Eco,
                            contentDescription = "Plnt",
                            tint = Color(0xFF2E7D32),
                            modifier = modifier
                                .size(40.dp)
                                .align(Alignment.BottomStart)
                        )
                    }
                }
                Column {
                    Text(title, style = MaterialTheme.typography.headlineSmall)
                }
            }
        },
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surface, tonalElevation = 8.dp) {
                HomeContent.entries.forEach { tab ->
                    val icon = when (tab) {
                        HomeContent.CULTURES -> Icons.Outlined.Spa
                        HomeContent.MAP -> Icons.Outlined.Map
                        HomeContent.FORCASTS -> Icons.Outlined.Cloud
                        HomeContent.PROFILE -> Icons.Outlined.Person
                    }
                    NavigationBarItem(
                        selected = tab == currentHomeTab,
                        onClick = { onSelectHomeTab(tab) },
                        icon = { Icon(icon, contentDescription = null) },
                        label = { Text(tab.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        ),
                    )
                }
            }
        },
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) { content() }
    }

}