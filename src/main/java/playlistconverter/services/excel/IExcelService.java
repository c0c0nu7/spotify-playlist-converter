package playlistconverter.services.excel;

import playlistconverter.domain.Playlist;

public interface IExcelService {

    void generateExcel(Playlist playlist);

}
