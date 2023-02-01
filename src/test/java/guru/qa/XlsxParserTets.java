package guru.qa;

import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class XlsxParserTets {

    ClassLoader cl = XlsxParserTets.class.getClassLoader();

    @Test
    void xmlParserTest() throws Exception {
        try (InputStream source = cl.getResourceAsStream("test2.zip");
             ZipInputStream zip = new ZipInputStream(source))
        {

            ZipEntry entry;
            while((entry = zip.getNextEntry()) != null) {
                assertThat(entry.getName()).isEqualTo("test2.xlsx");
                XLS content = new XLS(zip);
                assertThat(content.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue()).contains("first");
                assertThat(content.excel.getSheetAt(0).getRow(1).getCell(0).getStringCellValue()).contains("second");

            }
        }
    }

}