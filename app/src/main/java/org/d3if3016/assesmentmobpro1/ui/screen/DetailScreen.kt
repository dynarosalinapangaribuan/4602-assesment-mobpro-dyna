package org.d3if3016.assesmentmobpro1.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3016.assesmentmobpro1.R
import org.d3if3016.assesmentmobpro1.database.SeragamOlahragaDb
import org.d3if3016.assesmentmobpro1.ui.theme.AssesmentMobpro1Theme
import org.d3if3016.assesmentmobpro1.util.ViewModelFactory

const val KEY_ID_SERAGAM_OLAHRAGA = "idSeragamOlahraga"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null) {
    val context = LocalContext.current
    val db = SeragamOlahragaDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: DetailViewModel = viewModel(factory = factory)

    var namaPemesan by remember { mutableStateOf("") }
    var nomorTelepon by remember { mutableStateOf("") }
    var alamatPemesan by remember { mutableStateOf("") }
    var ukuran by remember { mutableStateOf("") }
    var jumlahPesanan by remember { mutableStateOf("") }

    LaunchedEffect(true) {
        if (id == null) return@LaunchedEffect
            val data = viewModel.getSeragamOlahraga(id) ?: return@LaunchedEffect
            namaPemesan = data.namaPemesan
            nomorTelepon = data.nomorTelepon
            alamatPemesan = data.alamatPemesan
            ukuran = data.ukuran
            jumlahPesanan = data.jumlahPesanan
        }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    if (id == null)
                        Text(text = stringResource(R.string.tambah_pesanan))
                    else
                        Text(text = stringResource(id = R.string.edit_pesanan))

                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = {
                        if (namaPemesan == "" || nomorTelepon == "" || alamatPemesan == "" || ukuran == "" || jumlahPesanan == "" ) {
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG). show()
                            return@IconButton
                        }
                        if (id == null) {
                            viewModel.insert(namaPemesan, nomorTelepon, alamatPemesan, ukuran, jumlahPesanan)
                        } else {
                            viewModel.update(id, namaPemesan, nomorTelepon, alamatPemesan, ukuran, jumlahPesanan)
                        }
                        navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(id = R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (id != null) {
                        DeleteAction {
                            viewModel.delete(id)
                            navController.popBackStack()
                        }
                    }
                }
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
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun DeleteAction(delete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(id = R.string.lainnya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(id = R.string.hapus))
                },
                onClick = {
                    expanded = false
                    delete()
                }
            )
        }
    }
}

@Composable
fun FormSeragamOlahraga(
    namaPemesan: String, onNamaPemesanChange: (String) -> Unit,
    nomorTelepon: String, onNomorTeleponChange: (String) -> Unit,
    alamatPemesan: String, onAlamatPemesanChange: (String) -> Unit,
    ukuran: String, onUkuranChange: (String) -> Unit,
    jumlahPesanan: String, onJumlahPesananChange: (String) -> Unit,
    modifier: Modifier
) {
    val radioOptions = listOf(
        stringResource(id = R.string.s),
        stringResource(id = R.string.m),
        stringResource(id = R.string.l),
        stringResource(id = R.string.xl),
        stringResource(id = R.string.xxl),
        stringResource(id = R.string.xxxl)
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, bottom = 168.dp)
            .padding(top = 16.dp),
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
                keyboardType = KeyboardType.Number,
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
        Column(
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            radioOptions.forEach {text ->
                ClassOption(
                    label = text,
                    isSelected = ukuran == text,
                    modifier = Modifier.selectable(
                        selected = ukuran == text,
                        onClick = {onUkuranChange(text)},
                        role = Role.RadioButton
                    )
                )
            }
        }
        OutlinedTextField(
            value = jumlahPesanan,
            onValueChange = { onJumlahPesananChange(it) },
            label = { Text(text = stringResource(id = R.string.jumlah_pesanan)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ClassOption(label: String, isSelected: Boolean, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    AssesmentMobpro1Theme {
        DetailScreen(rememberNavController())
    }
}