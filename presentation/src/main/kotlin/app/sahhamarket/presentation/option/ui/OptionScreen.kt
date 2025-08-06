package app.sahhamarket.presentation.option.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.resources.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.sahhamarket.compose.theme.DustyGray
import app.sahhamarket.presentation.auth.model.Country
import app.sahhamarket.presentation.auth.vm.OtpViewModel
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import app.sahhamarket.compose.theme.CaribbeanGreen
import app.sahhamarket.compose.theme.White
import kotlinx.coroutines.launch
import org.json.JSONObject


val countryList = listOf(
    Country("Saudi Arabia", "SA", "+966", R.drawable.saudi),
    Country("United Kingdom", "UK", "+44", R.drawable.uk),
)
@Composable
fun OptionScreen(
    goToOtpScreen: (String) -> Unit,
    viewModel: OtpViewModel = hiltViewModel()
) {
    var phone by remember { mutableStateOf("") }
    var keepSignedIn by remember { mutableStateOf(true) }
    var selectedCountry by remember { mutableStateOf(countryList[0]) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val fullPhone = "${selectedCountry.dialCode}$phone"
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = Modifier.fillMaxSize()) {
        OptionContent(
            phoneNumber = phone,
            onPhoneNumberChange = { phone = it },
            selectedCountry = selectedCountry,
            onCountrySelected = { selectedCountry = it },
            isKeepSignedIn = keepSignedIn,
            onKeepSignedInChange = { keepSignedIn = it },
            onGetOtpClick = {
                viewModel.setPhoneNumber(fullPhone)
                viewModel.requestOtp(
                    fullPhone,
                    onSuccess = {
                        goToOtpScreen(fullPhone)
                    },
                    onFailure = { error ->
                        val firstError = try {
                            val json = JSONObject(error)
                            val messageArray = json.optJSONArray("message")
                            if (messageArray != null && messageArray.length() > 0)
                                messageArray.getString(0)
                            else
                                json.optString("message", "Something went wrong")
                        } catch (e: Exception) {
                            "Something went wrong"
                        }
                        keyboardController?.hide()
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(firstError)
                        }
                    }
                )
            }
        )

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(MaterialTheme.spacing.s)
        )
    }
}

@Composable
fun OptionContent(
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    selectedCountry: Country,
    onCountrySelected: (Country) -> Unit,
    isKeepSignedIn: Boolean,
    onKeepSignedInChange: (Boolean) -> Unit,
    onGetOtpClick: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.m)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.xl))

        Text("Login", style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.fillMaxWidth().align(Alignment.Start))

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.ms))

        Text("Please enter your phone number", style = MaterialTheme.typography.labelLarge, color = DustyGray,modifier = Modifier.fillMaxWidth().align(Alignment.Start))

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.m))
        Text("Phone number", style = MaterialTheme.typography.bodyLarge,modifier = Modifier.fillMaxWidth().align(Alignment.Start))
        OutlinedTextField(
            value = "${selectedCountry.dialCode} ${phoneNumber}",
            onValueChange = { input ->
                val cleaned = input.removePrefix("${selectedCountry.dialCode} ").removePrefix(selectedCountry.dialCode)
                onPhoneNumberChange(cleaned.filter { it.isDigit() })
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            leadingIcon = {
                Box {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable { expanded = true }
                            .padding(start = MaterialTheme.spacing.xxxs)
                    ) {
                        Image(
                            painter = painterResource(id = selectedCountry.flagResId),
                            contentDescription = selectedCountry.name,
                            modifier = Modifier
                                .size(MaterialTheme.spacing.m)
                                .clip(MaterialTheme.shapes.extraLarge)
                        )
                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.xxs))
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        countryList.forEach { country ->
                            DropdownMenuItem(
                                text = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Image(
                                            painter = painterResource(id = country.flagResId),
                                            contentDescription = country.name,
                                            modifier = Modifier
                                                .size(MaterialTheme.spacing.m)
                                                .clip(CircleShape)
                                        )
                                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.xs))
                                        Text("${country.name} (${country.dialCode})")
                                    }
                                },
                                onClick = {
                                    onCountrySelected(country)
                                    onPhoneNumberChange("")
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.spacing.xl),
        )


        Spacer(modifier = Modifier.height(MaterialTheme.spacing.s))

        Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
            Checkbox(
                checked = isKeepSignedIn,
                onCheckedChange = onKeepSignedInChange,
                colors = CheckboxDefaults.colors(checkedColor = CaribbeanGreen)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.xs))
            Text("Keep me signed in")
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.m))

        Button(
            onClick = onGetOtpClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.spacing.xl),
            enabled = phoneNumber.isNotEmpty(),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = CaribbeanGreen)
        ) {
            Text("Get OTP", color = White , style = MaterialTheme.typography.titleSmall)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.m))

        Text("By proceeding, you agree to Rider’s terms", style = MaterialTheme.typography.bodySmall)
        Row {
            Text(
                "Terms & Conditions",
                style = MaterialTheme.typography.bodySmall.copy(color = CaribbeanGreen),
                modifier = Modifier.clickable { }
            )
            Text("  and  ")
            Text(
                "Privacy Policy.",
                style = MaterialTheme.typography.bodySmall.copy(color = CaribbeanGreen),
                modifier = Modifier.clickable { } ,

            )
        }
    }
}
