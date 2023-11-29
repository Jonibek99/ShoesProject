package com.example.myshoesapp.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myshoesapp.R
import com.example.myshoesapp.data.ShoesRepository
import com.example.myshoesapp.models.Shoes

@Composable
fun ShoesList(
    viewModel: ShoesListViewModel = ShoesListViewModel(ShoesRepository()),
    onAddNewShoesClick: () -> Unit,
    onShoesClick: (String) -> Unit = {}
) {
    val shoes by viewModel.shoesLiveData.observeAsState()

    if (!shoes.isNullOrEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(items = shoes!!, itemContent = { item ->
                ShoesItem(shoes = item)
            })
    }}
}



@Composable
private fun ShoesItem(shoes: Shoes) {
    ElevatedCard(
        modifier = Modifier
            .padding(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white), //Card background color
            contentColor = Color.Black  //Card content color,e.g.text
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    )
    {
        Column(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {  }
        ) {
            ShoesItemName(name = shoes.title)
            if (!shoes.description.isNullOrEmpty())
                ShoesItemDesc(desc = shoes.description)
        }
    }
}


@Composable
private fun ShoesItemName(name: String) {
    Text(
        text = name,
        fontSize = 21.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp)
    )
}

@Composable
private fun ShoesItemDesc(desc: String) {
    Text(
        text = desc,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = Color.Black,
        fontSize = 18.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Left
    )
}