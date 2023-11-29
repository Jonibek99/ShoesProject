package com.example.myshoesapp.detailedView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshoesapp.data.ShoesRepository
import com.example.myshoesapp.models.Shoes
import kotlinx.coroutines.launch

class DetailedViewModel(
    shoesId: String,
    private val shoesRepository: ShoesRepository
) : ViewModel() {

    val shoesLiveData: MutableLiveData<Shoes> by lazy {
        MutableLiveData<Shoes>()
    }

    init {
        getShoesById(shoesId)
    }

    private fun getShoesById(shoesId: String) {
        viewModelScope.launch {
            if (!shoesId.isNullOrEmpty()) {
                val shoes = shoesRepository.getShoesById(shoesId)
                shoesLiveData.value = shoes
            }
        }
    }

}