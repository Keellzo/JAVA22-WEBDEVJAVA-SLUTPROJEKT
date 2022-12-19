package model;

public class WeatherBean {

    private String cityStr;

    private String countryStr;

    private String cloudsStr;

    private String temperature;

    private String update;

    public WeatherBean(String cityStr, String countryStr) {
        this.cityStr = cityStr;
        this.countryStr = countryStr;
    }

    public String getCityStr() {
        return cityStr;
    }

    public String getCountryStr() {
        return countryStr;
    }

    public String getCloudsStr() {
        return cloudsStr;
    }

    public void setCloudsStr(String cloudsStr) {
        this.cloudsStr = cloudsStr;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }
}
