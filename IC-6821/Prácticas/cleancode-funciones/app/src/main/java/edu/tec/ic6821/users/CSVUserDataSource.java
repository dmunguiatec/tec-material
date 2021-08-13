package edu.tec.ic6821.users;

import lombok.NonNull;

import java.io.File;
import java.util.List;

public class CSVUserDataSource implements UserDataSource {

    private final File csvFile;

    public CSVUserDataSource(@NonNull File csvFile) {
        if (!csvFile.exists()) {
            throw new IllegalArgumentException(String.format("File %s does not exist", csvFile.getAbsolutePath()));
        }
        this.csvFile = csvFile;
    }

    /**
     * Cinco niveles de abstracción:
     * A. Procesar el archivo
     *      A.1 Procesar encabezado
     *      A.2 Procesar una línea del archivo
     *          A.2.i Validar línea contra encabezado
     *          A.2.ii Cargar línea en un map
     *          A.2.iii Transformar el map en un User
     *              A.2.iii.a Construir Address
     *                  A.2.iii.a.1 Construir Geolocation
     *              A.2.iii.b Construir Company
     *              A.2.iii.c Construir User
     */
    @Override
    public List<User> fetch() {

    }
}
