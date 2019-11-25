package io.arquitetura.hotelservice.configuration;

import io.arquitetura.hotelservice.client.CartaoClient;
import io.arquitetura.hotelservice.client.PagSeguroClient;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

@Configuration
public class ClientConfiguration {

	@Bean
	public PagSeguroClient pagSeguroClient() {
		return new Retrofit.Builder()
				.baseUrl("https://ws.sandbox.pagseguro.uol.com.br/v2/")
				.client(new OkHttpClient())
				.addConverterFactory(SimpleXmlConverterFactory.create())
				.build()
				.create(PagSeguroClient.class);
	}

	@Bean
	public CartaoClient cartaoClient() {
		return new Retrofit.Builder()
				.baseUrl("https://df.uol.com.br/v2/")
				.client(new OkHttpClient())
				.addConverterFactory(SimpleXmlConverterFactory.create())
				.build()
				.create(CartaoClient.class);
	}


}
