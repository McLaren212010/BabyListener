package TP6.babylistener.sendnotification


    import retrofit2.Call
    import retrofit2.http.Body
    import retrofit2.http.Headers
    import retrofit2.http.POST

public interface APIService {
        @Headers(
            "Content-Type:application/json",
            "Authorization:key=AAAAt_8k_gI:APA91bFesIL1Pm_Qm4mzbgVVGGvEVcv6h-g_J39V0n40nQZykgYCpBUO1r5U70Q-RF5kNA76bN_GLLi7Et3rJzlrf3u2vneNY8WjO3668e_xKaeRVxPup-uGbhSl3jR_mgdw19QZ9fmL" // server key
        )
        @POST("fcm/send")
        open fun sendNotification(@Body body: NotificationSender?): Call<MyResponse?>?
    }
