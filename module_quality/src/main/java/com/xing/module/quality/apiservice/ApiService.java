package com.xing.module.quality.apiservice;


import com.xing.commonbase.base.BaseResponse;
import com.xing.module.quality.bean.Plan;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface ApiService {


    //10.2.10.36
    //192.168.15.32
    @POST("http://10.2.10.36:8088/quality/tblschedulemain/getPlanByMonth")
    @FormUrlEncoded
    Observable<BaseResponse<List<Plan>>> getPlanByMonth(@Field("month") String month);

    @Multipart
    @POST("http://10.2.10.36:8088/quality/qcrecord/uploadqcRecord")
    Observable<BaseResponse> uploadQuality(@Part("data") String data, @PartMap Map<String, RequestBody> map);

}
