package guru.qa;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import static org.assertj.core.api.Assertions.assertThat;

public class CsvParserTest {

    ClassLoader cl = CsvParserTest.class.getClassLoader();

    @Test
    void csvParserTest() throws Exception {
        try (InputStream source = cl.getResourceAsStream("test3.zip");
             ZipInputStream zip = new ZipInputStream(source)) {

            ZipEntry entry;
            while((entry = zip.getNextEntry()) != null) {
                assertThat(entry.getName()).isEqualTo("test3.csv");
                CSVReader reader = new CSVReader(new InputStreamReader(zip));
                List<String[]> content = reader.readAll();
                assertThat(content.get(2)[0]).contains("five");
                assertThat(content.contains("nine ten"));
            }
        }
    }

}