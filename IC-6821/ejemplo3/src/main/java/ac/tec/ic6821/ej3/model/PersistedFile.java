package ac.tec.ic6821.ej3.model;

import java.io.InputStream;

public class PersistedFile {

    private String filename;
    private InputStream inputStream;

    public PersistedFile(String filename, InputStream inputStream) {
        this.filename = filename;
        this.inputStream = inputStream;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
