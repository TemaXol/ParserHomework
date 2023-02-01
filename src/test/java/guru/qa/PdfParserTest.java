package guru.qa;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class PdfParserTest {

    ClassLoader cl = PdfParserTest.class.getClassLoader();

    @Test
    void pdfParserTest() throws Exception {
        try (InputStream source = cl.getResourceAsStream("test1.zip");
             ZipInputStream zip = new ZipInputStream(source)) {

            ZipEntry entry;
            while((entry = zip.getNextEntry()) != null) {
                assertThat(entry.getName()).isEqualTo("test1.pdf");
                PDF content = new PDF(zip);
                assertThat(content.text).contains("last first");
                assertThat(content).containsText("Seconde");
            }
        }
    }
}
