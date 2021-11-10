package example.simplespringbootddd.libraries.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author chenweichuan
 */
public class JsonUtil {
    final private static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    final private static String DATE_FORMAT = "yyyy-MM-dd";
    final private static String TIME_FORMAT = "HH:mm:ss";

    final private static ObjectMapper OBJECT_MAPPER = createObjectMapper();

    public static String toJson(Object object) {
        String json;

        try {
            json = OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception exception) {
            json = null;
        }

        return json;
    }

    public static Object toObject(String json) {
        return toObject(json, Object.class);
    }

    public static <T> T toObject(String json, Class<T> objectType) {
        T object;

        try {
            object = OBJECT_MAPPER.readValue(json, objectType);
        } catch (Exception exception) {
            object = null;
        }

        return object;
    }

    public static <T> T toObject(String json, TypeReference<T> objectTypeRef) {
        T object;

        try {
            object = OBJECT_MAPPER.readValue(json, objectTypeRef);
        } catch (Exception exception) {
            object = null;
        }

        return object;
    }

    public static ObjectMapper createObjectMapper() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
        module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        module.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(TIME_FORMAT)));
        module.addSerializer(Date.class, new DateSerializer(false, new SimpleDateFormat(DATE_TIME_FORMAT)));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(TIME_FORMAT)));
        module.addDeserializer(Date.class, new DateDeserializers.DateDeserializer(DateDeserializers.DateDeserializer.instance, new SimpleDateFormat(DATE_TIME_FORMAT), DATE_TIME_FORMAT));

        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
        objectMapper.setDateFormat(TimeUtil.getSimpleDateFormat());
        objectMapper.registerModule(module);

        return objectMapper;
    }

}
