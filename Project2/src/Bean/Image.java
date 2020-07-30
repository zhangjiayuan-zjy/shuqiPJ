package Bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Image {
    private int imageID;
    private String title;
    private String description;
    private int cityCode;
    private String country_RegionCodeISO;
    private String path;
    private String content;
    private String date;
    private int UID;
    private String author;
    private long favorNumber;
    private String asciiName;
    private String countryName;

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public int getCityCode() {
        return cityCode;
    }

    public int getImageID() {
        return imageID;
    }

    public String getContent() {
        return content;
    }

    public String getCountry_RegionCodeISO() {
        return country_RegionCodeISO;
    }

    public String getDescription() {
        return description;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCountry_RegionCodeISO(String countryRegionCodeISO) {
        this.country_RegionCodeISO = countryRegionCodeISO;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        //DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //this.date=dateFormat.format(date);
        this.date = date;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setFavorNumber(long favorNumber) {
        this.favorNumber = favorNumber;
    }

    public long getFavorNumber() {
        return favorNumber;
    }

    public String getAsciiName() {
        return asciiName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setAsciiName(String asciiName) {
        this.asciiName = asciiName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
