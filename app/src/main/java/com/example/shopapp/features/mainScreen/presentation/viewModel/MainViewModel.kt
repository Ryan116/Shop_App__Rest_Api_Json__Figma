package com.example.shopapp.features.mainScreen.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.features.mainScreen.data.network.ShopMainApi
import com.example.shopapp.features.mainScreen.data.remote.RemoteDataSourceImpl
import com.example.shopapp.features.mainScreen.data.repository.MainScreenRepositoryImpl
import com.example.shopapp.features.mainScreen.domain.model.BestSeller
import com.example.shopapp.features.mainScreen.domain.model.HomeStore
import com.example.shopapp.features.mainScreen.domain.useCases.GetBestSellerListUseCase
import com.example.shopapp.features.mainScreen.domain.useCases.GetHomeStorePhonesListUseCase
import kotlinx.coroutines.launch

enum class ShopApiStatus { LOADING, ERROR, DONE }

class MainViewModel: ViewModel() {

    private val shopMainApiService = ShopMainApi.retrofitService
    private val remoteDataSource = RemoteDataSourceImpl(shopMainApiService)
    private val repository = MainScreenRepositoryImpl(remoteDataSource)
    private val getBestSellerListUseCase = GetBestSellerListUseCase(repository)
    private val getHomeStorePhonesListUseCase = GetHomeStorePhonesListUseCase(repository)

    private val _status = MutableLiveData<ShopApiStatus>()
    val status: LiveData<ShopApiStatus> = _status

    private val _phones = MutableLiveData<List<HomeStore>>()
    val phones: LiveData<List<HomeStore>> = _phones

    private val _bestSellerPhonesList = MutableLiveData<List<BestSeller>>()
    val bestSellerPhonesList: LiveData<List<BestSeller>> = _bestSellerPhonesList

    var homeStoreListSize = MutableLiveData<Int>()
    val bestSellerListSize = MutableLiveData<Int>()


    init {
        getPhoneModels()
    }



    private fun getPhoneModels() {

        viewModelScope.launch {
            _status.value = ShopApiStatus.LOADING
                try {
                    _phones.value = getHomeStorePhonesListUseCase.getHomeStorePhonesList()
                    _bestSellerPhonesList.value = getBestSellerListUseCase.getBestSellerPhonesList()
                    _status.value = ShopApiStatus.DONE
                } catch (e: Exception) {
                    _status.value = ShopApiStatus.ERROR
                }
        }
    }
}