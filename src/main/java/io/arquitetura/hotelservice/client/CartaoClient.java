package io.arquitetura.hotelservice.client;


import io.arquitetura.hotelservice.dto.Cartao;
import io.arquitetura.hotelservice.dto.Session;
import io.arquitetura.hotelservice.dto.Transaction;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CartaoClient {

    @FormUrlEncoded
    @POST("cards")
    Call<Cartao> buscarToken(@Field("sessionId") String sessionId,
                                @Field("amount") String amount,
                                @Field("cardNumber") String cardNumber,
                                @Field("cardBrand") String cardBrand,
                                @Field("cardCvv") String cardCvv,
                                @Field("cardExpirationMonth") String cardExpirationMonth,
                                @Field("cardExpirationYear") String cardExpirationYear
    );
}