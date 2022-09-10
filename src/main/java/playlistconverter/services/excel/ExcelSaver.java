package playlistconverter.services.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ExcelSaver {

    @Value("${excel.path}")
    private String path;

    public void save(HSSFWorkbook workbook) {
        FileOutputStream fileOut;

        try {
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("The excel file has been saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
