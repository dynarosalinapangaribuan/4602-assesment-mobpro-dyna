package org.d3if3016.assesmentmobpro1.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import org.d3if3016.assesmentmobpro1.model.OpStatus
import org.d3if3016.assesmentmobpro1.model.Tanaman
import retrofit2.http.DELETE
import retrofit2.http.Query


private const val BASE_URL = "https://unspoken.my.id/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface TanamanApiService {
    @GET("api_dyna.php")
    suspend fun getTanaman(
        @Header("Authorization") userId: String
    ): List<Tanaman>

    @Multipart
    @POST("api_dyna.php")
    suspend fun postTanaman(
        @Header("Authorization") userId: String,
        @Part("namaTumbuhan") namaTumbuhan: RequestBody,
        @Part("namaLatin") namaLatin: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("api_dyna.php")
    suspend fun deleteTanaman(
        @Header("Authorization") userId: String,
        @Query("id") id: String
    ): OpStatus

}

object TanamanApi {
    val service: TanamanApiService by lazy {
        retrofit.create(TanamanApiService::class.java)
    }

    fun getTanamanUrl(imageId: String): String {
        return "${BASE_URL}image.php?id=$imageId"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }