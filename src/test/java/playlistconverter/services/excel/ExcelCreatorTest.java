package playlistconverter.services.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import playlistconverter.domain.Artist;
import playlistconverter.domain.Playlist;
import playlistconverter.domain.Track;
import playlistconverter.testutils.TestUtils;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ExcelCreatorTest {

    ExcelCreator creator;

    @BeforeEach
    public void beforeEach() {
        creator = new ExcelCreator();
    }

    @Test
    void testCreate() {
        Track track1 = TestUtils.createTrack("Raining Blood", "Slayer");
        Track track2 = TestUtils.createTrack("One", "Metallica");

        Playlist playlist = Playlist.builder()
                .tracks(List.of(track1, track2))
                .build();

        HSSFWorkbook result = creator.create(playlist);

        HSSFSheet sheet = result.getSheetAt(0);

        assertThat(sheet.getRow(1).getCell(0).getStringCellValue()).isEqualTo("Raining Blood");
        assertThat(sheet.getRow(1).getCell(1).getStringCellValue()).isEqualTo("Slayer");
        assertThat(sheet.getRow(2).getCell(0).getStringCellValue()).isEqualTo("One");
        assertThat(sheet.getRow(2).getCell(1).getStringCellValue()).isEqualTo("Metallica");
    }


}