package Bean;

public class Country {
    private String ISO;
    private String country_RegionName;

    public void setCountry_RegionName(String country_RegionName) {
        this.country_RegionName = country_RegionName;
    }

    public void setISO(String ISO) {
        this.ISO = ISO;
    }

    public String getCountry_RegionName() {
        return country_RegionName;
    }

    public String getISO() {
        return ISO;
    }
}
