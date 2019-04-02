package fr.zomet.tech.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import fr.zomet.tech.dto.Hidden;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    private static final String MASK = "XXXXXXXXXX";

    @Bean
    public ObjectMapper objectMapper() {
        return new Jackson2ObjectMapperBuilder()
                .indentOutput(false)
                .filters(filterProvider())
                .build();
    }

    private SimpleFilterProvider filterProvider() {
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.setDefaultFilter(propertyFilter());
        return filterProvider;
    }

    private PropertyFilter propertyFilter() {
        return new SimpleBeanPropertyFilter() {
            @Override
            public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
                if (writer.getAnnotation(Hidden.class) != null) {
                    BeanWrapper pojoBean = PropertyAccessorFactory.forBeanPropertyAccess(pojo);
                    if (pojoBean.getPropertyValue(writer.getName()) != null) {
                        jgen.writeStringField(writer.getName(), MASK);
                        return;
                    }
                }
                writer.serializeAsField(pojo, jgen, provider);

            }
        };
    }
}
