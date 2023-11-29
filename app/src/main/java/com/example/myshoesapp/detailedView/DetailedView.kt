package com.example.myshoesapp.detailedView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myshoesapp.R
import com.example.myshoesapp.data.ShoesRepository
import com.example.myshoesapp.models.Shoes

@Composable
fun DetailedView(shoesId: String,
                 viewModel: DetailedViewModel = DetailedViewModel(shoesId, ShoesRepository())
) {

    val shoes by viewModel.shoesLiveData.observeAsState()

    if(shoes != null)
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Title(title = shoes!!.title)
            Spacer(Modifier.weight(1f))
        }
        Description(description = shoes!!.description)
        MyDivider()
        Spacer(Modifier.height(16.dp))
        Founders(founders = shoes!!.founders)
        Gender(gender = shoes!!.gender)
        Size(size = shoes!!.size)
        Price(price = shoes!!.price)

    }
}

@Composable
fun Title (title: String){
    Text(
        text = title,
        color = Color.Black,
        fontSize = 30.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}
@Composable
fun Description (description: String){
    Text(
        text = description,
        color = Color.Black,
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}

@Composable
fun Founders (founders: List<String>){
    Column(modifier = Modifier.fillMaxWidth()) {
        var i = 0
        for (founder in founders) {
            FoundersTextView(founder = founder, ++i == founders.size)
        }
    }
}


//@Composable
//fun MyImage(){
//    Box() {
//        AsyncImage(model = , contentDescription = )
//    }
//}
//Image(
//painter = painterResource(id = R.drawable.baseline_adb_24),
//contentDescription = stringResource(id = R.string.app_name)
//)


@Composable
fun Gender (gender: String){
    Text(
        text = gender,
        color = Color.Black,
        fontSize = 16.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}

@Composable
fun Size (size: Int){
    Text(
        text = "$size",
        color = Color.Black,
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}

@Composable
fun Budget (budget: Int){
    Text(
        modifier = Modifier.padding(bottom = 3.dp),
        text = stringResource(id = R.string.detailed_view_budget_label,
            budget),
        color = Color.Black,
        fontSize = 15.sp,
        fontFamily = FontFamily.SansSerif
    )
}


@Composable
fun Price (price: Double){
    Text(
        text = "$$price",
        color = Color.Black,
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun FoundersTextView(founder: String, isTheLastOne: Boolean) {
    Text(
        modifier = Modifier.padding(6.dp, 3.dp),
        text = if (isTheLastOne) founder else "$founder,",
        color = Color.DarkGray,
        fontSize = 19.sp,
        fontFamily = FontFamily.SansSerif,
        fontStyle = FontStyle.Italic
    )
}

@Composable
private fun MyDivider() {
    Divider(
        color = Color.LightGray
    )
}
