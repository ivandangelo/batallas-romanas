package com.interfaz.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class LectorCS implements LectorDeLegiones {

	
	HashMap<String, RegistroDeLegion> registro = new HashMap<>();
	public static HashSet<File> archivosCS = new HashSet<>();

	public LectorCS(String PATH) {
		File folder = new File(PATH);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (listOfFiles[i].getName().endsWith(".cs") || listOfFiles[i].getName().endsWith(".CS")) {
					archivosCS.add(listOfFiles[i]);
				}
			}
		}
	}

	public HashMap<String, RegistroDeLegion> leerLegion() throws FileNotFoundException, IOException  {
		for (File archivo : archivosCS) {
			try (Reader reader = new FileReader(archivo);
					CSVParser csvParser = new CSVParser(reader,
							CSVFormat.DEFAULT.withDelimiter(',').withIgnoreSurroundingSpaces(true));) {

				for (CSVRecord csvRecord : csvParser) {
					String[] sublegiones = null;
					if (csvRecord.size() - 4 > 0) {
						sublegiones = new String[csvRecord.size() - 4];
					}
					registro.put(csvRecord.get(0),
							new RegistroDeLegion(csvRecord.get(0), sublegiones,
									Integer.parseInt(csvRecord.get(csvRecord.size() - 3)),
									Integer.parseInt(csvRecord.get(csvRecord.size() - 2)),
									Integer.parseInt(csvRecord.get(csvRecord.size() - 1))));
					for (int i = csvRecord.size() - 4, j = 0; i >= 1; i--, j++) {
						sublegiones[j] = csvRecord.get(i);
					}
				}
			}
		}
		return registro;
	}

}
