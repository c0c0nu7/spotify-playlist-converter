package playlistconverter.services.excel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import playlistconverter.domain.Artist;
import playlistconverter.domain.Playlist;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExcelCreator {

    public static final String SHEET_NAME = "FirstSheet";
    public static final String TRACK_COLUMN_TITLE = "Track";
    public static final String ARTIST_COLUMN_TITLE = "Artist";

    public HSSFWorkbook create(Playlist playlist) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(SHEET_NAME);

        createRow(sheet, (short) 0, TRACK_COLUMN_TITLE, ARTIST_COLUMN_TITLE);

        playlist.getTracks().forEach(
                track ->
                        createRow(sheet, sheet.getLastRowNum() + 1, track.getName(), createArtistString(track.getArtists()))
        );

        return workbook;
    }

    private void createRow(HSSFSheet sheet, int rowNumber, String name, String artistString) {
        HSSFRow row = sheet.createRow(rowNumber);
        row.createCell(0).setCellValue(name);
        row.createCell(1).setCellValue(artistString);
    }

    private String createArtistString(List<Artist> artists) {
        return StringUtils.join(artists.stream()
                                        .map(Artist::getName)
                                        .collect(Collectors.toList()), ',');
    }

}
