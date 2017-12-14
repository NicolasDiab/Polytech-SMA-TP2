package sma.travelBooking;

import java.time.ZonedDateTime;

public class PlaneTicket {
    private ZonedDateTime dateTimeDeparture;
    private ZonedDateTime dateTimeArrival;

    private String departure;
    private String arrival;

    private Double price;

    private Company company;

    public PlaneTicket(ZonedDateTime dateTimeDeparture, ZonedDateTime dateTimeArrival, String Departure,
                       String arrival, Double price) {
        this.dateTimeDeparture = dateTimeDeparture;
        this.dateTimeArrival = dateTimeArrival;
        this.departure = Departure;
        this.arrival = arrival;
        this.price = price;
    }

    public ZonedDateTime getDateTimeDeparture() {
        return dateTimeDeparture;
    }

    public void setDateTimeDeparture(ZonedDateTime dateTimeDeparture) {
        this.dateTimeDeparture = dateTimeDeparture;
    }

    public ZonedDateTime getDateTimeArrival() {
        return dateTimeArrival;
    }

    public void setDateTimeArrival(ZonedDateTime dateTimeArrival) {
        this.dateTimeArrival = dateTimeArrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
