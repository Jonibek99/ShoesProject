package com.example.myshoesapp.addNew

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myshoesapp.MainActivity
import com.example.myshoesapp.R
import com.example.myshoesapp.data.ShoesRepository
import com.example.myshoesapp.models.Shoes

import java.text.ParseException
import java.text.SimpleDateFormat


@Composable
fun AddNewShoes(
    viewModel: AddNewShoesViewModel = AddNewShoesViewModel(ShoesRepository())
) {
    val localContext = LocalContext.current

    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val gender = remember{ mutableStateOf("") }
    val founders = remember { mutableStateOf("") }
    val size = remember { mutableStateOf("") }

    val response by viewModel.insertResponseLiveData.observeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            CreateNewShoesPageTitle()
            Spacer(modifier = Modifier.height(15.dp))
            TitleInput(title = title.value, onTitleChange = { title.value = it })
            Spacer(Modifier.height(16.dp))
            DescriptionInput(description = description.value,
                onDescriptionChange = { description.value = it })
            Spacer(modifier = Modifier.height(15.dp))
            Price(price = price.value, onPriceChanged = { price.value = it })
            Spacer(modifier = Modifier.height(15.dp))
            Gender(gender = gender.value, onGenderChange = { gender.value = it })
            Spacer(modifier = Modifier.height(15.dp))
            FoundersInput(founders = founders.value, onFoundersChange = { founders.value = it })
            Spacer(modifier = Modifier.height(15.dp))
            Size(size = size.value, onSizeChanged = { size.value = it })
            Spacer(modifier = Modifier.height(15.dp))

            // actors text input - comma separated 4x

            Spacer(Modifier.height(16.dp))
            AddNewButton {
                val constructedShoes: Shoes? = constructShoesIfInputValid(
                    titleInput = title.value,
                    descriptionInput = description.value,
                    priceInput = price.value,
                    genderInput = gender.value,
                    foundersInput = founders.value,
                    sizeInput = size.value,

                    context = localContext
                )


                if (constructedShoes != null
                ) {
                    viewModel.saveNewShoes(
                        constructedShoes
                    )
                }
            }
        }

        if (response != null) {
            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.Center),
                fontSize = 19.sp,
                text = if (response!!.status == "OK") stringResource(id = R.string.saved_success_msg)
                else stringResource(id = R.string.saved_fail_msg)
            )

            if (response!!.status == "OK") {
                localContext.startActivity(Intent(localContext, MainActivity::class.java))
            }
        }
    }

}

@Composable
private fun CreateNewShoesPageTitle() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.title_activity_add_new_Shoes),
        color = Color.Black,
        fontSize = 26.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TitleInput(title: String, onTitleChange: (String) -> Unit) {
    TextField(modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black, containerColor = colorResource(id = R.color.teal_200)
        ),
        value = title,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onTitleChange(it) },
        label = {
            Text(stringResource(id = R.string.Shoes_title_input_hint))
        })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DescriptionInput(description: String, onDescriptionChange: (String) -> Unit) {
    TextField(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black, containerColor = colorResource(id = R.color.teal_200)
        ),
        value = description,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onDescriptionChange(it) },
        label = {
            Text(stringResource(id = R.string.Shoes_desc_input_hint))
        })
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FoundersInput(founders: String, onFoundersChange: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = colorResource(id = R.color.teal_200)
        ),
        value = founders,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onFoundersChange(it) },
        label = {
            Text(stringResource(id = R.string.add_new_founders_input_hint))
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Gender(gender: String, onGenderChange: (String) -> Unit) {
    TextField(modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black, containerColor = colorResource(id = R.color.teal_200)
        ),
        value = gender,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onGenderChange(it) },
        label = {
            Text(stringResource(id = R.string.Shoes_gender_input_hint))
        })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Price(price: String, onPriceChanged: (String) -> Unit) {
    TextField(modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black, containerColor = colorResource(id = R.color.teal_200)
        ),
        value = price,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { onPriceChanged(it) },
        label = {
            Text(stringResource(id = R.string.shoes_price_input_hint))
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Size(size: String, onSizeChanged: (String) -> Unit) {
    TextField(modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black, containerColor = colorResource(id = R.color.teal_200)
        ),
        value = size,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { onSizeChanged(it) },
        label = {
            Text(stringResource(id = R.string.shoes_size_input_hint))
        })
}


@Composable
private fun AddNewButton(onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(vertical = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.teal_200), contentColor = Color.Black
        )

    ) {
        Text(
            fontSize = 17.sp, text = stringResource(id = R.string.save_btn_text)
        )
    }
}

private fun constructShoesIfInputValid(
    titleInput: String?,
    descriptionInput: String?,
    priceInput: String?,
    genderInput: String,
    foundersInput: String?,
    sizeInput: String?,
    context: Context
): Shoes? {
    if (titleInput.isNullOrEmpty() ||
        descriptionInput.isNullOrEmpty() ||
        priceInput.isNullOrEmpty() ||
        genderInput.isNullOrEmpty() ||
        foundersInput.isNullOrEmpty() ||
        sizeInput.isNullOrEmpty()
    ) {
        Toast.makeText(
            context, context.resources.getString(R.string.Shoes_all_fields_compulsory_warning),
            Toast.LENGTH_SHORT
        ).show()
        return null
    }


    return Shoes(
        title = titleInput,
        description = descriptionInput,
        price = priceInput.toDouble(),
        founders = foundersInput.split(","),
        gender = genderInput,
        size = sizeInput.toInt()
    )
}