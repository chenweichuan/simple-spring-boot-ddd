package example.simplespringbootddd.infrastructure.config;

import example.simplespringbootddd.libraries.utils.JsonUtil;
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
@Configuration
public class FeignConfig {
    final private static Pattern ENV_COOKIE_NAME_PATTERN = Pattern.compile("^(passthrough_cookine_name_pattern)$");

    @Bean
    Client client() {
        return new OkHttpClient(
                new okhttp3.OkHttpClient.Builder()
                        .addInterceptor(new RequestInterceptor())
                        .build()
        );
    }

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

    private static class RequestInterceptor implements Interceptor {
        @Override
        public @NonNull Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request newRequest = request.newBuilder()
                    .addHeader("Cookie", buildCookieHeaderValue())
                    .build();
            return chain.proceed(newRequest);
        }
    }

    private static String buildCookieHeaderValue() {
        Cookie[] cookies=((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getCookies();
        StringBuilder valueBuilder = new StringBuilder();
        for (Cookie cookie: cookies) {
            try {
                if (ENV_COOKIE_NAME_PATTERN.matcher(cookie.getName()).matches()) {
                    valueBuilder.append(URLEncoder.encode(cookie.getName(), StandardCharsets.UTF_8.toString()));
                    valueBuilder.append("=");
                    valueBuilder.append(URLEncoder.encode(cookie.getValue(), StandardCharsets.UTF_8.toString()));
                    valueBuilder.append(";");
                }
            } catch (Exception ignored) {
            }
        }
        return valueBuilder.toString();
    }
}
