package Bean;

public class City {
    private int geoNameID;
    private String asciiName;
    private String country_RegionCodeISO;

    public void setAsciiName(String asciiName) {
        this.asciiName = asciiName;
    }

    public void setCountry_RegionCodeISO(String country_RegionCodeISO) {
        this.country_RegionCodeISO = country_RegionCodeISO;
    }

    public void setGeoNameID(int geoNameID) {
        this.geoNameID = geoNameID;
    }

    public String getAsciiName() {
        return asciiName;
    }

    public int getGeoNameID() {
        return geoNameID;
    }

    public String getCountry_RegionCodeISO() {
        return country_RegionCodeISO;
    }
}
