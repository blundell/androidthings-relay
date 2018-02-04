package com.blundell.driver;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;

public class Relay {

    public static Relay newInstance(String gpioPin) {
        PeripheralManagerService service = new PeripheralManagerService();
        return newInstance(service, gpioPin);
    }

    public static Relay newInstance(PeripheralManagerService service, String gpioPin) {
        try {
            Gpio gpio = service.openGpio(gpioPin);
            gpio.setActiveType(Gpio.ACTIVE_HIGH);
            gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            return new Relay(gpio);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private final Gpio gpio;

    Relay(Gpio gpio) {
        this.gpio = gpio;
    }

    public void connectTo(Position position) {
        try {
            switch (position) {
                case NORMALLY_OPEN:
                    gpio.setValue(true);
                    break;
                case NORMALLY_CLOSED:
                    gpio.setValue(false);
                    break;
                default:
                    throw new IllegalStateException("Developer Error, unhandled enum " + position);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot switch to " + position, e);
        }
    }

    public void close() {
        try {
            gpio.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public enum Position {
        NORMALLY_OPEN, NORMALLY_CLOSED
    }

}
