package com.blundell.tut

import android.app.Activity
import android.os.Bundle
import com.blundell.driver.RelayKotlin

class MainKotlinActivity : Activity() {

    lateinit var relay: RelayKotlin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relay = RelayKotlin.newInstance("GPIO21")
        relay.connectTo(RelayKotlin.PositionKotlin.NORMALLY_CLOSED)
    }

    override fun onDestroy() {
        super.onDestroy()
        relay.close()
    }
}
