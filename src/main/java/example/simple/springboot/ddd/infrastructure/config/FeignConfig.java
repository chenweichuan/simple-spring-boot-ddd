package example.simple.springboot.ddd.infrastructure.config;

import example.simple.springboot.ddd.libraries.utils.JsonUtil;
import feign.Contract;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

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
