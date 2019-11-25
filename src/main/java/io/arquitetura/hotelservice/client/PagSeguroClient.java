package io.arquitetura.hotelservice.client;


import io.arquitetura.hotelservice.dto.Session;
import io.arquitetura.hotelservice.dto.Transaction;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PagSeguroClient {

    @POST("transactions/refunds")
    Call<Void> estornarCompra(@Query(value = "email") String email,
                                 @Query(value = "token") String token,
                                 @Query(value = "transactionCode") String transactionCode);

    @POST("sessions")
    Call<Session> criarSessao(@Query(value = "email") String email,
                              @Query(value = "token") String token);

    @FormUrlEncoded
    @POST("transactions")
    Call<Transaction> criarTransacao(@Query(value = "email") String email,
                                     @Query(value = "token") String token,
                                    @Field("paymentMode") String paymentMode,
                                    @Field("paymentMethod") String paymentMethod,
                                 @Field("receiverEmail") String receiverEmail,
                                 @Field("currency") String currency,
                                 @Field("itemId1") String itemId1,
                                 @Field("itemDescription1") String itemDescription1,
                                 @Field("itemAmount1") String itemAmount1,
                                 @Field("itemQuantity1") String itemQuantity1,
                                 @Field("notificationURL") String notificationURL,
                                 @Field("reference") String reference,
                                 @Field("senderName") String senderName,
                                 @Field("senderCPF") String senderCPF,
                                 @Field("senderAreaCode") String senderAreaCode,
                                 @Field("senderPhone") String senderPhone,
                                 @Field("senderEmail") String senderEmail,
                                 @Field("senderIp") String senderIp,
                                 @Field("shippingAddressStreet") String shippingAddressStreet,
                                 @Field("shippingAddressNumber") String shippingAddressNumber,
                                 @Field("shippingAddressComplement") String shippingAddressComplement,
                                 @Field("shippingAddressDistrict") String shippingAddressDistrict,
                                 @Field("shippingAddressPostalCode") String shippingAddressPostalCode,
                                 @Field("shippingAddressCity") String shippingAddressCity,
                                 @Field("shippingAddressState") String shippingAddressState,
                                 @Field("shippingAddressCountry") String shippingAddressCountry,
                                 @Field("shippingType") String shippingType,
                                 @Field("shippingCost") String shippingCost,
                                 @Field("creditCardToken") String creditCardToken,
                                 @Field("installmentQuantity") String installmentQuantity,
                                 @Field("installmentValue") String installmentValue,
                                 @Field("noInterestInstallmentQuantity") String noInterestInstallmentQuantity,
                                 @Field("creditCardHolderName") String creditCardHolderName,
                                 @Field("creditCardHolderCPF") String creditCardHolderCPF,
                                 @Field("creditCardHolderBirthDate") String creditCardHolderBirthDate,
                                 @Field("creditCardHolderAreaCode") String creditCardHolderAreaCode,
                                 @Field("creditCardHolderPhone") String creditCardHolderPhone,
                                 @Field("billingAddressStreet") String billingAddressStreet,
                                 @Field("billingAddressNumber") String billingAddressNumber,
                                 @Field("billingAddressComplement") String billingAddressComplement,
                                 @Field("billingAddressDistrict") String billingAddressDistrict,
                                 @Field("billingAddressPostalCode") String billingAddressPostalCode,
                                 @Field("billingAddressCity") String billingAddressCity,
                                 @Field("billingAddressState") String billingAddressState,
                                 @Field("billingAddressCountry") String billingAddressCountry
                                 );
}