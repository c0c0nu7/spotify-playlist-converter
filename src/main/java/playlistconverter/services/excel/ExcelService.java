package playlistconverter.services.excel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import playlistconverter.domain.Artist;
import playlistconverter.domain.Playlist;

import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExcelService {

    @Value("${excel.path}")
    private String path;

    public void createExcel(Playlist playlist) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");

            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell(0).setCellValue("Track");
            rowhead.createCell(1).setCellValue("Artist");

            playlist.getTracks().forEach(
                    track -> {
                        HSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
                        row.createCell(0).setCellValue(track.getName());
                        row.createCell(1).setCellValue(createArtistString(track.getArtists()));
                    }
            );

            FileOutputStream fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("The excel file has been created!");

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private String createArtistString(List<Artist> artists) {
        return StringUtils.join(artists.stream().map(Artist::getName).collect(Collectors.toList()), ',');
    }

}
