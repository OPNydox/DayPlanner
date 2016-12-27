package app;

import app.domain.dtos.JSON.importDtos.CameraImportJsonDto;
import app.domain.dtos.JSON.importDtos.LensImportJsonDto;
import app.domain.dtos.JSON.importDtos.PhotographerImportJsonDto;
import app.domain.dtos.XML.ImportDtos.AccessoryImportXmlDto;
import app.io.interfaces.FileIO;
import app.parser.interfaces.FileParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Terminal implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {

    }

}








