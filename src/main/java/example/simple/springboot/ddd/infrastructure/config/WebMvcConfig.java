package example.simple.springboot.ddd.infrastructure.config;

import example.simple.springboot.ddd.libraries.utils.JsonUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.TemporalAccessorParser;
import org.springframework.format.datetime.standard.TemporalAccessorPrinter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author chenweichuan
 */
@Configuration
@ControllerAdvice
public class WebMvcConfig extends WebMvcConfigurationSupport {
    final private static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    final private static String DATE_FORMAT = "yyyy-MM-dd";
    final private static String TIME_FORMAT = "HH:mm:ss";

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
        converters.add(new MappingJackson2HttpMessageConverter(JsonUtil.createObjectMapper()));
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldType(LocalDateTime.class, new TemporalAccessorPrinter(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)), new TemporalAccessorParser(LocalDateTime.class, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
        registry.addFormatterForFieldType(LocalDate.class, new TemporalAccessorPrinter(DateTimeFormatter.ofPattern(DATE_FORMAT)), new TemporalAccessorParser(LocalDate.class, DateTimeFormatter.ofPattern(DATE_FORMAT)));
        registry.addFormatterForFieldType(LocalTime.class, new TemporalAccessorPrinter(DateTimeFormatter.ofPattern(TIME_FORMAT)), new TemporalAccessorParser(LocalTime.class, DateTimeFormatter.ofPattern(TIME_FORMAT)));
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }
}
