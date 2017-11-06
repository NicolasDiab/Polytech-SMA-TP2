package service;

import java.text.SimpleDateFormat;

public class PlaneTicket {

    private SimpleDateFormat dateTimeDeparture;
    private SimpleDateFormat dateTimeArrival;

    private Airport airportDeparture;
    private Airport airportArrival;

    private Double price;

    private Company company;

    public PlaneTicket(SimpleDateFormat dateTimeDeparture, SimpleDateFormat dateTimeArrival, Airport airportDeparture,
                       Airport airportArrival, Double price) {
        this.dateTimeDeparture = dateTimeDeparture;
        this.dateTimeArrival = dateTimeArrival;
        this.airportDeparture = airportDeparture;
        this.airportArrival = airportArrival;
        this.price = price;
    }

    public SimpleDateFormat getDateTimeDeparture() {
        return dateTimeDeparture;
    }

    public void setDateTimeDeparture(SimpleDateFormat dateTimeDeparture) {
        this.dateTimeDeparture = dateTimeDeparture;
    }

    public SimpleDateFormat getDateTimeArrival() {
        return dateTimeArrival;
    }

    public void setDateTimeArrival(SimpleDateFormat dateTimeArrival) {
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
