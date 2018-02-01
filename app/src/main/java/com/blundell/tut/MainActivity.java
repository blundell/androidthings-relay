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
        setContentView(R.layout.activity_main);

        relay = Relay.newInstance("GPIO21");
        relay.switchTo(Relay.Position.CLOSED);

        relays = Relays.newInstance("GPIO17", "GPIO18");
        relays.switchAllTo(Relay.Position.OPEN);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        relay.close();
        relays.close();
    }
}
