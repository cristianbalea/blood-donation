package domain;


import java.sql.Date;

public class Appointment {
    private Integer id;

    private Location location;

    private Donor donor;

    private Date date;

    private boolean confirmed;

    public Appointment() {
    }

    public Appointment(Location location, Donor donor, Date date) {
        this.location = location;
        this.donor = donor;
        this.date = date;
        this.confirmed = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", location=" + location +
                ", donor=" + donor +
                ", date=" + date +
                '}';
    }
}
