package com.example.shoppingapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.model.ShoeData
import kotlinx.coroutines.launch

class MainViewmodel : ViewModel() {
    val service = RetrofitService.getInstance().create(ApiService::class.java)
    val data : MutableState<ShoeData?>? = mutableStateOf(null)
    fun fetchData(){
        viewModelScope.launch {
             data?.value =service.getData()
        }
    }
}