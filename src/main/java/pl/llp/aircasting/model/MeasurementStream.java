package pl.llp.aircasting.model;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class MeasurementStream {
    private List<Measurement> measurements = newArrayList();
    private String sensorName;
    private String measurementType;
    private String unit;
    private String symbol;

    private double sum;
    private double peak = Double.NEGATIVE_INFINITY;

    public MeasurementStream(String sensorName, String measurementType, String unit, String symbol) {
        this.sensorName = sensorName;
        this.measurementType = measurementType;
        this.unit = unit;
        this.symbol = symbol;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void add(Measurement measurement) {
        measurements.add(measurement);

        double value = measurement.getValue();
        sum += value;
        if (value > peak) {
            peak = value;
        }
    }

    public String getSensorName() {
        return sensorName;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public String getUnit() {
        return unit;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getAvg() {
        return sum / (measurements.isEmpty() ? 1 : measurements.size());
    }

    public double getPeak() {
        return peak;
    }
}
