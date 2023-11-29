package com.example.myshoesapp.addNew

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshoesapp.data.ShoesRepository
import com.example.myshoesapp.data.network.response.MyResponse
import com.example.myshoesapp.models.Shoes
import kotlinx.coroutines.launch

class AddNewShoesViewModel(private val ShoesRepository: ShoesRepository) : ViewModel() {

    val insertResponseLiveData: MutableLiveData<MyResponse> by lazy {
        MutableLiveData<MyResponse>()
    }

    fun saveNewShoes(Shoes: Shoes) {
        viewModelScope.launch {
            try {

                val response = ShoesRepository.insertNewShoes(Shoes)
//                insertResponseLiveData.value = response

                Log.d("Update_response", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}