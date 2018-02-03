package com.blundell.driver

import com.blundell.driver.RelayKotlin.PositionKotlin.NORMALLY_CLOSED
import com.blundell.driver.RelayKotlin.PositionKotlin.NORMALLY_OPEN
import com.google.android.things.pio.Gpio
import com.google.android.things.pio.PeripheralManagerService

class RelayKotlin(val gpio: Gpio) {

    companion object Factory {
        fun newInstance(gpioPin: String): RelayKotlin {
            return newInstance(PeripheralManagerService(), gpioPin)
        }

        fun newInstance(service: PeripheralManagerService, gpioPin: String): RelayKotlin {
            val gpio = service.openGpio(gpioPin)
            gpio.setActiveType(Gpio.ACTIVE_HIGH)
            gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW)
            return RelayKotlin(gpio)
        }
    }

    fun connectTo(position: PositionKotlin) {
        when (position) {
            NORMALLY_OPEN -> gpio.setValue(true)
            NORMALLY_CLOSED -> gpio.setValue(false)
            else -> {
                throw IllegalStateException("Developer error, unhandled enum " + position)
            }
        }
    }

    fun close() {
        gpio.close()
    }

    enum class PositionKotlin {
        NORMALLY_OPEN, NORMALLY_CLOSED
    }

}
