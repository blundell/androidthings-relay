package com.blundell.tut;

import android.app.Activity;
import android.os.Bundle;

import com.blundell.driver.Relay;
import com.blundell.driver.Relays;

import org.jetbrains.annotations.Nullable;

class MainActivity extends Activity {

    private Relay relay;
    private Relays relays;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        relay = Relay.newInstance("GPIO21");
        relay.connectTo(Relay.Position.NORMALLY_CLOSED);

        relays = Relays.newInstance("GPIO17", "GPIO18");
        relays.connectAllTo(Relay.Position.NORMALLY_OPEN);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        relay.close();
        relays.close();
    }
}
