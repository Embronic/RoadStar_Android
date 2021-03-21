package com.road.star.api;


import com.road.star.models.AccessTokenResponse;
import com.road.star.models.base.BaseResponse;
import com.road.star.models.city.CityResponse;
import com.road.star.models.forgotPasswordOtp.ForgotPasswordOTPResponse;
import com.road.star.models.login.LoginResponse;
import com.road.star.models.otp.OTPResponse;
import com.road.star.models.registration.RegistrationResponse;
import com.road.star.models.state.StatesResponse;
import com.road.star.models.verfiyPasswordOTP.VerifyForgotPasswordOTPResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("user-otp")
    Call<OTPResponse> getOTP(@Field("mobile") String mobile,
                             @Field("country_phone_code") String countryPhoneCode,
                             @Field("country_name") String countryName,
                             @Field("country_code") String countryCode);

    @FormUrlEncoded
    @POST("user-otp-verification")
    Call<BaseResponse> verifyOTP(@Field("mobile") String mobile,
                                 @Field("country_phone_code") String countryPhoneCode,
                                 @Field("otp") String otp);

    @FormUrlEncoded
    @POST("user-login")
    Call<LoginResponse> Login(@Field("email") String email,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("user-forgot-password-otp")
    Call<ForgotPasswordOTPResponse> getForgotPasswordOTP(@Field("email") String email);

    @FormUrlEncoded
    @POST("verify-user-forgot-password-otp")
    Call<VerifyForgotPasswordOTPResponse> verifyPasswordOTP(@Field("mobile") String mobile,
                                                            @Field("country_phone_code") String countryPhoneCode,
                                                            @Field("otp") String otp);

    @FormUrlEncoded
    @POST("change-user-password")
    Call<VerifyForgotPasswordOTPResponse> updatePassword(@Field("mobile") String mobile,
                                                         @Field("country_phone_code") String countryPhoneCode,
                                                         @Field("password") String password,
                                                         @Field("confirm_password") String confirmPassword);

    @FormUrlEncoded
    @POST("all-states")
    Call<StatesResponse> getStates(@Field("country_id") String countryId);

    @FormUrlEncoded
    @POST("all-cities")
    Call<CityResponse> getCity(@Field("state_id") String stateId);

    @FormUrlEncoded
    @POST("add-user")
    Call<RegistrationResponse> addUser(@Field("first_name") String firstName,
                                              @Field("last_name") String lastName,
                                              @Field("mobile") String mobile,
                                              @Field("country_phone_code") String countryPhoneCode,
                                              @Field("address") String address,
                                              @Field("state_id") String stateId,
                                              @Field("city_id") String cityId,
                                              @Field("email") String email,
                                              @Field("password") String password);

}
