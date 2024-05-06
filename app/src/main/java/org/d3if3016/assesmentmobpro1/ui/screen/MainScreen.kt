package org.d3if3016.assesmentmobpro1.ui.screen

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.d3if3016.assesmentmobpro1.R
import org.d3if3016.assesmentmobpro1.model.SeragamOlahraga
import org.d3if3016.assesmentmobpro1.ui.theme.AssesmentMobpro1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssesmentMobpro1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.nama_aplikasi))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Toast.makeText(context, R.string.tambah_error, Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.tambah_pesanan),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier) {
    val viewModel: MainViewModel = viewModel()
    val data = viewModel.data
    val context = LocalContext.current

    if (data.isEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.list_kosong), 
                contentDescription = "Empty data image!",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.list_kosong))
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 84.dp)
        ) {
            items(data) {
                ListSeragamOlahraga(seragamOlahraga = it) {
                    val pesan = context.getString(R.string.x_diklik, it.namaPemesan)
                    Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show()
                }
                Divider()
            }
        }
    }
}

@Composable
fun ListSeragamOlahraga(seragamOlahraga: SeragamOlahraga, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = seragamOlahraga.namaPemesan,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold)
        Text(text = seragamOlahraga.nomorTelepon,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis)
        Text(text = seragamOlahraga.alamatPemesan,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis)
        Text(text = seragamOlahraga.ukuran,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis)
        Text(text = seragamOlahraga.jumlahPesanan,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis)
        Text(text = seragamOlahraga.tanggal)
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    AssesmentMobpro1Theme {
        MainScreen()
    }
}