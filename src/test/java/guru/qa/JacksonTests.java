package guru.qa;

import example.qa.ExampleWork;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;


public class JacksonTests {

    @Test
    void jsonParserTest() throws Exception {
//
        ObjectMapper objectMapper = new ObjectMapper();


            File file = new File("src/test/resources/example.json");

            ExampleWork employee = objectMapper.readValue(file, ExampleWork.class);

            assertThat(employee.author).isEqualTo("Chef Cook");
            assertThat(employee.menu.menuitem).contains("vat");
            assertThat(employee.menu.menuitem).contains("net");
            assertThat(employee.menu.id).isEqualTo("file");
            assertThat(employee.menu.popup).isEqualTo("232");
            assertThat(employee.menu.values).isEqualTo("File");
        }

}
