package com.example.inventory.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.inventory.InventoryTopAppBar
import com.example.inventory.R
import com.example.inventory.settings.AppPreferences
import com.example.inventory.ui.navigation.NavigationDestination

object SettingsDestination : NavigationDestination {
    override val route = "settings"
    override val titleRes = R.string.settings
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SettingsScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val prefs = remember { AppPreferences(context) }
    var hideSensitive by remember { mutableStateOf(prefs.hideSensitiveData) }
    var disableShare by remember { mutableStateOf(prefs.disableShare) }
    var useDefaultQuantity by remember { mutableStateOf(prefs.useDefaultQuantity) }
    var defaultQuantity by remember { mutableStateOf(prefs.defaultQuantity.toString()) }

    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = stringResource(R.string.settings),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Скрывать чувствительные данные", Modifier.weight(1f))
                Switch(
                    checked = hideSensitive,
                    onCheckedChange = {
                        hideSensitive = it
                        prefs.hideSensitiveData = it
                    }
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Запретить отправку данных", Modifier.weight(1f))
                Switch(
                    checked = disableShare,
                    onCheckedChange = {
                        disableShare = it
                        prefs.disableShare = it
                    }
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Использовать дефолтное количество", Modifier.weight(1f))
                Switch(
                    checked = useDefaultQuantity,
                    onCheckedChange = {
                        useDefaultQuantity = it
                        prefs.useDefaultQuantity = it
                    }
                )
            }

            OutlinedTextField(
                value = defaultQuantity,
                onValueChange = {
                    defaultQuantity = it
                    prefs.defaultQuantity = it.toIntOrNull() ?: 1
                },
                label = { Text("Дефолтное количество") },
                modifier = Modifier.fillMaxWidth(),
                enabled = useDefaultQuantity,
            )
        }
    }
}