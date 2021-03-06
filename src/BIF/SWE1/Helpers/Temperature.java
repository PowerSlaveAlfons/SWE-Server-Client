package BIF.SWE1.Helpers;

/**
 * A class that serves as interchangeable object
 * between the sensor and the H2 Database
 */
public class Temperature {
    private double temperature;
    private int seconds;
    private String date;

    /**
     * Creates a new Object
     * @param temperature current temperature in celcius
     * @param seconds past seconds of the day
     * @param date current date formatted YYYY-MM-DD
     */
    public Temperature (double temperature, int seconds, String date) {
        this.temperature = temperature;
        this.seconds = seconds;
        this.date = date;
    }

    /**
     * Creates a INSERT Query based on the given data
     * @return String of the Query
     */
    public String createQuery() {
        StringBuilder builder = new StringBuilder();
        builder.append("insert into TEMPERATURE (VALUE, DATE) VALUES(");
        builder.append(temperature).append(", '");
        builder.append(date).append("')");
        return builder.toString();
    }

    /**
     * Returns the temperature in celcius
     * @return double of temperature
     */
    public double getValue() {
        return temperature;
    }

}