package ac.tec.ic6821.ej3.model.vo;

public class ProfilePhotoVO {

    private String username;
    private String filename;
    private String photo;

    public ProfilePhotoVO() {
    }

    public ProfilePhotoVO(String username, String filename, String photo) {
        this.username = username;
        this.filename = filename;
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
