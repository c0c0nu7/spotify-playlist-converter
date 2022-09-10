package playlistconverter.services.excel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import playlistconverter.domain.Playlist;
import playlistconverter.interfaces.IExcelService;

@RequiredArgsConstructor
@Service
public class ExcelService implements IExcelService {

    ExcelSaver excelSaver;
    ExcelCreator excelCreator;

    public void generateExcel(Playlist playlist) {
        excelSaver.save(excelCreator.create(playlist));
        System.out.println("The excel file has been created!");
    }

}
