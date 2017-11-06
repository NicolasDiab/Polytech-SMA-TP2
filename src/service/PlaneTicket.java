package service;

import java.time.ZonedDateTime;

public class PlaneTicket {

    private ZonedDateTime dateTimeDeparture;
    private ZonedDateTime dateTimeArrival;

    private Airport airportDeparture;
    private Airport airportArrival;

    private Double price;

    private Company company;

    public PlaneTicket(ZonedDateTime dateTimeDeparture, ZonedDateTime dateTimeArrival, Airport airportDeparture,
                       Airport airportArrival, Double price) {
        this.dateTimeDeparture = dateTimeDeparture;
        this.dateTimeArrival = dateTimeArrival;
        this.airportDeparture = airportDeparture;
        this.airportArrival = airportArrival;
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

    public Airport getAirportDeparture() {
        return airportDeparture;
    }

    public void setAirportDeparture(Airport airportDeparture) {
        this.airportDeparture = airportDeparture;
    }

    public Airport getAirportArrival() {
        return airportArrival;
    }

    public void setAirportArrival(Airport airportArrival) {
        this.airportArrival = airportArrival;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
