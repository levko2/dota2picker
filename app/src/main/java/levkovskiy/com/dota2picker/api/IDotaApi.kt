package levkovskiy.com.dota2picker.api

import io.reactivex.Observable
import levkovskiy.com.dota2picker.Utils.BASE_URL
import levkovskiy.com.dota2picker.model.DataResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.TreeSet

interface IDotaApi {

    @GET("api/heroStats")
    fun heroStats(): Observable<List<DataResponse.Hero>>

    @GET("api/heroes/{id}/matchups")
    fun getMatchup(@Path("id") id: Int): Observable<TreeSet<DataResponse.Matchup>>

    companion object Factory {
        fun create(): IDotaApi {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(IDotaApi::class.java)
        }
    }
}