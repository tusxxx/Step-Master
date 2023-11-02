package com.tusxapps.step_master.utils

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier


fun enableLogging() {
    Napier.base(DebugAntilog())
}