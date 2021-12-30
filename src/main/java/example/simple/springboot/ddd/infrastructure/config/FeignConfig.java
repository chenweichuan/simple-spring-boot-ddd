package example.simple.springboot.ddd.infrastructure.config;

import example.simple.springboot.ddd.libraries.utils.JsonUtil;
import feign.Client;
import feign.Contract;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.okhttp.OkHttpClient;
import lombok.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author chenweichuan
 */
public class FeignConfig {
    @Bean
    Encoder encoder() {
        return new SpringEncoder(()-> new HttpMessageConverters(new MappingJackson2HttpMessageConverter(JsonUtil.createObjectMapper())));
    }

    @Bean
    Decoder decoder() {
        return new SpringDecoder(()-> new HttpMessageConverters(new MappingJackson2HttpMessageConverter(JsonUtil.createObjectMapper())));
    }

    @Bean
    Contract contract() {
        return new SpringMvcContract();
    }

    @Bean
    Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }
}
