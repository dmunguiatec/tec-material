package edu.tec.ic6821.users;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class CSVUserDataSource implements UserDataSource {
    private static final String SEPARATOR = ",";

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_USERNAME = "username";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_ADDRESS_STREET = "address_street";
    private static final String FIELD_ADDRESS_SUITE = "address_suite";
    private static final String FIELD_ADDRESS_CITY = "address_city";
    private static final String FIELD_ADDRESS_ZIPCODE = "address_zipcode";
    private static final String FIELD_GEOLOCATION_LATITUDE = "address_geo_lat";
    private static final String FIELD_GEOLOCATION_LONGITUDE = "address_geo_lng";
    private static final String FIELD_PHONE = "phone";
    private static final String FIELD_WEBSITE = "website";
    private static final String FIELD_COMPANY_NAME = "company_name";
    private static final String FIELD_COMPANY_CATCH_PHRASE = "company_catchPhrase";
    private static final String FIELD_COMPANY_BS = "company_bs";

    private static final String EMPTY_FIELD_VALUE = "";


    private final File csvFile;

    public CSVUserDataSource(final File csvFile) {
        if (!csvFile.exists()) {
            throw new IllegalArgumentException(String.format("File %s does not exist", csvFile.getAbsolutePath()));
        }
        this.csvFile = csvFile;
    }

    /**
     * Cinco niveles de abstracción:
     * A. Procesar el archivo
     * A.1 Procesar encabezado
     * A.2 Procesar una línea del archivo
     * A.2.i Validar línea contra encabezado
     * A.2.ii Cargar línea en un map
     * A.2.iii Transformar el map en un User
     * A.2.iii.a Construir Address
     * A.2.iii.a.1 Construir Geolocation
     * A.2.iii.b Construir Company
     * A.2.iii.c Construir User
     */
    @Override
    public List<User> fetch() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.csvFile))) {
            final List<User> users = new ArrayList<>();

            final String headerLine = reader.readLine();
            final Optional<String[]> header = processHeader(headerLine);
            if (header.isEmpty()) {
                return Collections.emptyList();
            }

            String line;
            while ((line = reader.readLine()) != null) {
                final Optional<User> user = processLine(header.get(), line);
                if (user.isPresent()) {
                    users.add(user.get());
                }
            }

            return users;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<String[]> processHeader(String headerLine) {
        if (headerLine == null) {
            return Optional.empty();
        }

        return Optional.of(headerLine.split(SEPARATOR));
    }

    private Optional<User> processLine(String[] header, String line) {
        final String[] values = line.split(SEPARATOR);
        if (!validateLine(header, values)) {
            return Optional.empty();
        }

        final Map<String, String> row = loadRow(header, values);
        return Optional.of(loadUser(row));
    }

    private boolean validateLine(String[] header, String[] values) {
        return header.length == values.length;
    }

    private Map<String, String> loadRow(String[] header, String[] values) {
        final Map<String, String> row = new HashMap<>();
        for (int i = 0; i < header.length; i++) {
            row.put(header[i], values[i]);
        }

        return row;
    }

    private User loadUser(Map<String, String> row) {

        final Address address = loadAddress(row);
        final Company company = loadCompany(row);

        return new User(
                Integer.parseInt(row.getOrDefault(FIELD_ID, EMPTY_FIELD_VALUE)),
                row.getOrDefault(FIELD_NAME, EMPTY_FIELD_VALUE),
                row.getOrDefault(FIELD_USERNAME, EMPTY_FIELD_VALUE),
                row.getOrDefault(FIELD_EMAIL, EMPTY_FIELD_VALUE),
                address,
                row.getOrDefault(FIELD_PHONE, EMPTY_FIELD_VALUE),
                row.getOrDefault(FIELD_WEBSITE, EMPTY_FIELD_VALUE),
                company
        );
    }

    private Address loadAddress(Map<String, String> row) {
        final Geolocation geolocation = loadGeolocation(row);

        return new Address(
                row.getOrDefault(FIELD_ADDRESS_STREET, EMPTY_FIELD_VALUE),
                row.getOrDefault(FIELD_ADDRESS_SUITE, EMPTY_FIELD_VALUE),
                row.getOrDefault(FIELD_ADDRESS_CITY, EMPTY_FIELD_VALUE),
                row.getOrDefault(FIELD_ADDRESS_ZIPCODE, EMPTY_FIELD_VALUE),
                geolocation
        );
    }

    private Geolocation loadGeolocation(Map<String, String> row) {
        return new Geolocation(
                row.getOrDefault(FIELD_GEOLOCATION_LATITUDE, EMPTY_FIELD_VALUE),
                row.getOrDefault(FIELD_GEOLOCATION_LONGITUDE, EMPTY_FIELD_VALUE)
        );
    }

    private Company loadCompany(Map<String, String> row) {
        return new Company(
                row.getOrDefault(FIELD_COMPANY_NAME, EMPTY_FIELD_VALUE),
                row.getOrDefault(FIELD_COMPANY_CATCH_PHRASE, EMPTY_FIELD_VALUE),
                row.getOrDefault(FIELD_COMPANY_BS, EMPTY_FIELD_VALUE)
        );
    }
}
