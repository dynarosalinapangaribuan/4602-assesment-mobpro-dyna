package org.d3if3016.assesmentmobpro1.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.d3if3016.assesmentmobpro1.R
import org.d3if3016.assesmentmobpro1.ui.theme.AssesmentMobpro1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen() {
    var namaPemesan by remember { mutableStateOf("") }
    var nomorTelepon by remember { mutableStateOf("") }
    var alamatPemesan by remember { mutableStateOf("") }
    var ukuran by remember { mutableStateOf("") }
    var jumlahPesanan by remember { mutableStateOf("") }
    var tanggal by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.tambah_pesanan))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) {padding ->
        FormSeragamOlahraga(
            namaPemesan = namaPemesan,
            onNamaPemesanChange = { namaPemesan = it },
            nomorTelepon = nomorTelepon,
            onNomorTeleponChange = { nomorTelepon = it },
            alamatPemesan = alamatPemesan,
            onAlamatPemesanChange = { alamatPemesan = it },
            ukuran = ukuran,
            onUkuranChange = { ukuran = it },
            jumlahPesanan = jumlahPesanan,
            onJumlahPesananChange = { jumlahPesanan = it },
            tanggal = tanggal,
            onTanggalChange = { tanggal = it },
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormSeragamOlahraga(
    namaPemesan: String, onNamaPemesanChange: (String) -> Unit,
    nomorTelepon: String, onNomorTeleponChange: (String) -> Unit,
    alamatPemesan: String, onAlamatPemesanChange: (String) -> Unit,
    ukuran: String, onUkuranChange: (String) -> Unit,
    jumlahPesanan: String, onJumlahPesananChange: (String) -> Unit,
    tanggal: String, onTanggalChange: (String) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = namaPemesan,
            onValueChange = { onNamaPemesanChange(it) },
            label = { Text(text = stringResource(id = R.string.nama_pemesan)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nomorTelepon,
            onValueChange = { onNomorTeleponChange(it) },
            label = { Text(text = stringResource(id = R.string.nomor_telepon)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = alamatPemesan,
            onValueChange = { onAlamatPemesanChange(it) },
            label = { Text(text = stringResource(id = R.string.alamat_pemesan)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = ukuran,
            onValueChange = { onUkuranChange(it) },
            label = { Text(text = stringResource(id = R.string.ukuran)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = jumlahPesanan,
            onValueChange = { onJumlahPesananChange(it) },
            label = { Text(text = stringResource(id = R.string.jumlah_pesanan)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = tanggal,
            onValueChange = { onTanggalChange(it) },
            label = { Text(text = stringResource(id = R.string.tanggal)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    AssesmentMobpro1Theme {
        DetailScreen()
    }
}