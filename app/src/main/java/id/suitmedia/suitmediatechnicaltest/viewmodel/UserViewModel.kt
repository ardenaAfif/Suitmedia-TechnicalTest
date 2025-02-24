package id.suitmedia.suitmediatechnicaltest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.suitmedia.suitmediatechnicaltest.data.remote.ApiClient
import id.suitmedia.suitmediatechnicaltest.data.reoi.UserResponse
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _userResponse = MutableLiveData<UserResponse>()
    val userResponse: LiveData<UserResponse> get() = _userResponse

    init {
        fetchUsers(1)
    }

    private fun fetchUsers(page: Int) {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getUsers(page)
                _userResponse.value = response
            } catch (e: Exception) {
                // Handle failure
                e.printStackTrace()
            }
        }
    }
}