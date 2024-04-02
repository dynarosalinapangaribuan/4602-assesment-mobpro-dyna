package org.d3if3016.assesmentmobpro1.ui.screen

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3016.assesmentmobpro1.R
import org.d3if3016.assesmentmobpro1.navigation.Screen
import org.d3if3016.assesmentmobpro1.navigation.SetupNavGraph
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
                    SetupNavGraph()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFD20062),
                    titleContentColor = Color(0xFFFFFFFF),
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.About.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(id = R.string.tentang_aplikasi),
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier) {
    var nama by rememberSaveable { mutableStateOf("") }
    var namaError by rememberSaveable { mutableStateOf(false) }

    var nim by rememberSaveable { mutableStateOf("") }
    var nimError by rememberSaveable { mutableStateOf(false) }

    var bpp by rememberSaveable { mutableStateOf("") }
    var bppError by rememberSaveable { mutableStateOf(false) }

    var semester by rememberSaveable { mutableStateOf("") }
    var semesterError by rememberSaveable { mutableStateOf(false) }

    var hitungHasil by rememberSaveable { mutableFloatStateOf(0f) }

    val radioOptions = listOf(
        stringResource(id = R.string.pria),
        stringResource(id = R.string.wanita)
    )
    var gender by rememberSaveable { mutableStateOf(radioOptions[0]) }

    var pilihProdi by rememberSaveable { mutableStateOf("") }
    var pilihProdiError by rememberSaveable { mutableStateOf(false) }

    var expanded by rememberSaveable { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    val dropdownOptions = stringArrayResource(id = R.array.dropdown_options)

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.bpp_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nama,
            onValueChange = { nama = it },
            label = { Text(text = stringResource(id = R.string.nama_mahasiswa)) },
            isError = namaError,
            trailingIcon = { IconPicker(isError = namaError, unit = "") },
            supportingText = { ErrorHint(isError = namaError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nim,
            onValueChange = { nim = it },
            label = { Text(text = stringResource(id = R.string.nim_mahasiswa)) },
            isError = nimError,
            trailingIcon = { IconPicker(isError = nimError, unit = "") },
            supportingText = { ErrorHint(isError = nimError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            radioOptions.forEach { text ->
                GenderOption(
                    label = text,
                    isSelected = gender == text,
                    modifier = Modifier
                        .selectable(
                            selected = gender == text,
                            onClick = { gender = text },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray)
                .padding(4.dp)
        ) {
            dropdownOptions.forEach {option ->
                DropdownMenuItem(
                    option = option,
                    onClick = {
                        pilihProdi = option
                        expanded = false
                    },
                    enable = expanded
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray)
        ) {
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
            }

            LaunchedEffect(expanded) {
                if (expanded) {
                    scrollState.scrollTo(scrollState.maxValue)
                }
            }
        }

        if (pilihProdiError && pilihProdi.isBlank()) {
            Text(
                text = stringResource(id = R.string.input_invalid),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 4.dp
                    )
                    .fillMaxWidth(),
                color = Color.Red,
                textAlign = TextAlign.Start
            )
        }

        if (pilihProdi.isNotBlank()) {
            Text(
                text = "Pilihan prodi : $pilihProdi ",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
        // Akhir DropdownMenu

        OutlinedTextField(
            value = bpp,
            onValueChange = { bpp = it },
            label = { Text(text = stringResource(id = R.string.uang_bpp)) },
            isError = bppError,
            leadingIcon = { Text(text = "Rp")},
            trailingIcon = { IconPicker(isError = bppError, unit = "") },
            supportingText = { ErrorHint(isError = bppError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = semester,
            onValueChange = { semester = it },
            label = { Text(text = stringResource(id = R.string.jumlah_semester)) },
            isError = semesterError,
            trailingIcon = { IconPicker(isError = semesterError, unit = "") },
            supportingText = { ErrorHint(isError = semesterError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    namaError = (nama == "" || nama == "0")
                    nimError = (nim == "" || nim == "0")
                    bppError = (bpp == "" || bpp == "0")
                    semesterError = (semester == "" || semester == "0")
                    pilihProdiError = (pilihProdi == "" || pilihProdi == "0")
                    if (namaError || nimError || bppError || semesterError || pilihProdiError) return@Button

                    hitungHasil = hitungJumlah(bpp.toFloat(), semester.toFloat())
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.hitung))
            }

            Button(
                onClick = {
                    nama = ""
                    nim = ""
                    bpp = ""
                    semester = ""
                    gender = radioOptions[0]
                    pilihProdi = ""
                    hitungHasil = 0f
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.reset))
            }
        }

        if (hitungHasil != 0f) {
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = stringResource(id = R.string.hitung_x, hitungHasil),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription =null )
    }
    else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(id = R.string.input_invalid))
    }
}

fun hitungJumlah(bpp: Float, semester: Float): Float {
    return bpp * semester
}

@Composable
fun DropdownMenuItem(
    option: String,
    onClick: () -> Unit,
    enable: Boolean
) {
    Text(
        text = option,
        modifier = Modifier
            .selectable(
                selected = enable,
                onClick = onClick,
                enabled = enable,
                role = Role.DropdownList
            )
            .padding(16.dp)
    )
}


@Composable
    fun GenderOption(label: String, isSelected: Boolean, modifier: Modifier) {
        Row(
            modifier = modifier,
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
fun ScreenPreview() {
    AssesmentMobpro1Theme {
        MainScreen(rememberNavController())
    }
}