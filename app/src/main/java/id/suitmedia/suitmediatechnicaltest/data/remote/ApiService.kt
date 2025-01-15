package id.suitmedia.suitmediatechnicaltest.data.remote

import id.suitmedia.suitmediatechnicaltest.data.reoi.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int,
    ): UserResponse
}