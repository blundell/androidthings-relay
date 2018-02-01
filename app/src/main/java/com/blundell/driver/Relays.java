package com.blundell.driver;

import com.google.android.things.pio.PeripheralManagerService;

import java.util.ArrayList;
import java.util.List;

public class Relays {

    public static Relays newInstance(String... gpioPins) {
        if (gpioPins.length == 0) {
            throw new IllegalStateException(
                "Need to pass some gpio pins name parameters to create relays",
                new ArrayIndexOutOfBoundsException(0)
            );
        }

        PeripheralManagerService service = new PeripheralManagerService();
        List<Relay> relays = new ArrayList<>();
        for (String gpioPin : gpioPins) {
            relays.add(Relay.newInstance(service, gpioPin));
        }

        return new Relays(relays);
    }

    private final List<Relay> relays;

    Relays(List<Relay> relays) {
        this.relays = relays;
    }

    public void switchAllTo(Relay.Position position) {
        for (Relay relay : relays) {
            relay.switchTo(position);
        }
    }

    public void close() {
        for (Relay relay : relays) {
            relay.close();
        }
    }
}
