package com.example.myshoesapp.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshoesapp.data.ShoesRepository
import com.example.myshoesapp.models.Shoes
import kotlinx.coroutines.launch

data class ShoesListViewModel(
    private val shoesRepository: ShoesRepository) : ViewModel() {
    val shoesLiveData: MutableLiveData<List<Shoes>> by lazy { MutableLiveData<List<Shoes>>()
    }

    init {
        getAllShoes()
    }

    fun getAllShoes() {
        viewModelScope.launch {
            val shoes = shoesRepository.getShoesList()
            shoesLiveData.value = shoes
        }
    }
}
