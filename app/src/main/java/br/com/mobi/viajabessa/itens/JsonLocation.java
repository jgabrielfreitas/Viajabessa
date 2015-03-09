package br.com.mobi.viajabessa.itens;

/**
 * Created by JGabrielFreitas on 25/02/15.
 */
public class JsonLocation {

    private Integer type;
    private String title;
    private String description;
    private String value;
    private String imageUrl;
    private String latitude;
    private String longitude;

    public JsonLocation(Integer type, String title, String description, String value, String imageUrl, String latitude, String longitude) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.value = value;
        this.imageUrl = imageUrl;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
