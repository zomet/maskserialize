package fr.zomet.tech.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.zomet.tech.dto.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SerializationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void serialize_user_with_hidden_params() throws IOException {
        UserDto userDto = createTestUser();
        String serializedUser = objectMapper.writeValueAsString(userDto);
        Assertions.assertThat(serializedUser).isEqualTo("{\"id\":\"1\",\"birthDate\":\"XXXXXXXXXX\",\"cardNumber\":\"XXXXXXXXXX\",\"fullName\":\"Foo Bar\"}");
    }

    private UserDto createTestUser() {
        UserDto userDto = new UserDto();
        userDto.setId("1");
        userDto.setFullName("Foo Bar");
        userDto.setCardNumber("123456789");
        userDto.setBirthDate(LocalDate.parse("19700101", DateTimeFormatter.ofPattern("yyyyMMdd")));
        return userDto;
    }
}
