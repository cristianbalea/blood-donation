package domain;

public class Location {
    private Integer id;
    private String name;
    private String address;
    private String area;
    private int openHour;
    private int closingHour;
    private Boolean opened;
    private int maxNumberOfDonors;

    public Location() {
    }

    public Location(String name, String address, String area, int openHour,
                    int closingHour, Boolean opened, int maxNumberOfDonors) {
        this.name = name;
        this.address = address;
        this.area = area;
        this.openHour = openHour;
        this.closingHour = closingHour;
        this.opened = opened;
        this.maxNumberOfDonors = maxNumberOfDonors;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getOpenHour() {
        return openHour;
    }

    public void setOpenHour(int openHour) {
        this.openHour = openHour;
    }

    public int getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(int closingHour) {
        this.closingHour = closingHour;
    }

    public Boolean getOpened() {
        return opened;
    }

    public void setOpened(Boolean opened) {
        this.opened = opened;
    }

    public int getMaxNumberOfDonors() {
        return maxNumberOfDonors;
    }

    public void setMaxNumberOfDonors(int maxNumberOfDonors) {
        this.maxNumberOfDonors = maxNumberOfDonors;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", area='" + area + '\'' +
                ", openHour=" + openHour +
                ", closingHour=" + closingHour +
                ", opened=" + opened +
                ", maxNumberOfDonors=" + maxNumberOfDonors +
                '}';
    }
}
