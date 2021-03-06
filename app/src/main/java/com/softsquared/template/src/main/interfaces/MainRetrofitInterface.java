package com.softsquared.template.src.main.interfaces;

import com.softsquared.template.src.main.models.DayForecastResponse;
import com.softsquared.template.src.main.models.EtcResponse;
import com.softsquared.template.src.main.models.HourForecastResponse;
import com.softsquared.template.src.preview.models.JapanResponse;
import com.softsquared.template.src.main.models.NoticeResponse;
import com.softsquared.template.src.main.models.RegionResponse;
import com.softsquared.template.src.main.models.GradeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainRetrofitInterface {
    @GET("/dust/value")
    Call<RegionResponse> getRegion(@Query("region") String region);

    @GET("/dust/etc")
    Call<EtcResponse> getEtc(@Query("region") String region);

    @GET("/dust/etc")
    Call<EtcResponse> getEtc2(@Query("x") Double x, @Query("y") Double y);

    @GET("/dust/grade")
    Call<GradeResponse> getGrade(@Query("region") String region);

    @GET("/rtdbGet ")
    Call<NoticeResponse> getNotice();

    @GET("/hourForecast")
    Call<HourForecastResponse> getHourForecast();

    @GET("/dayForecast")
    Call<DayForecastResponse> getDayForecast();


}
