package ir.fallahpoor.cleankmmdemo.rocketlaunches

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.fallahpoor.cleankmmdemo.R
import ir.fallahpoor.cleankmmdemo.domain.model.RocketLaunch
import ir.fallahpoor.cleankmmdemo.presentation.RocketLaunchesScreenUiState
import ir.fallahpoor.cleankmmdemo.presentation.RocketLaunchesViewModel
import ir.fallahpoor.cleankmmdemo.theme.Space
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketLaunchesScreen(rocketLaunchesViewModel: RocketLaunchesViewModel = koinViewModel()) {
    val uiState: RocketLaunchesScreenUiState by rocketLaunchesViewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        rocketLaunchesViewModel.getRocketLaunches()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (uiState) {
                is RocketLaunchesScreenUiState.Loading -> Loading(modifier = Modifier.fillMaxSize())
                is RocketLaunchesScreenUiState.Success -> RocketLaunchesList(
                    modifier = Modifier.fillMaxSize(),
                    rocketLaunches = (uiState as RocketLaunchesScreenUiState.Success).rocketLaunches
                )
                is RocketLaunchesScreenUiState.Error -> Error(
                    modifier = Modifier.fillMaxSize(),
                    message = (uiState as RocketLaunchesScreenUiState.Error).message
                )
            }
        }
    }
}

@Composable
private fun RocketLaunchesList(rocketLaunches: List<RocketLaunch>, modifier: Modifier = Modifier) {
    if (rocketLaunches.isEmpty()) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(R.string.no_rocket_launches))
        }
    } else {
        LazyColumn(modifier = modifier) {
            items(rocketLaunches) { rocketLaunch ->
                RocketLaunchItem(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = Space.LARGE, vertical = Space.NORMAL),
                    rocketLaunch = rocketLaunch
                )
            }
        }
    }
}

@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun Error(message: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = message)
    }
}