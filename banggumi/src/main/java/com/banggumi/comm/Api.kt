package com.banggumi.comm

import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


/**
 * @author Lai
 * @time 2018/7/25 16:19
 * @Description
 */
interface Api {

    @GET("oauth/authorize")
    fun getOAuthCode(@Query("client_id") clientId: String, @Query("response_type") code: String): Observable<RequestBody>

    @POST("oauth/access_token")
    fun getAccessToken(@Query("grant_type") grant_type: String
                       , @Query("client_id") client_id: String
                       , @Query("client_secret") client_secret: String
                       , @Query("code") code: String
                       , @Query("redirect_uri") redirect_uri: String): Observable<String>


}