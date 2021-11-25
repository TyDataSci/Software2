package model;

public class Country {
    int countryId;
    String countryName;
    String createDate;
    String createdBy;
    String lastUpdate;
    String lastUpdatedBy;

    public Country(int countryId, String countryName, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }


    public int getCountryId() {
        return  countryId;
    }
    public String getCountryName(){
        return countryName;
    }
    public String getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

}
