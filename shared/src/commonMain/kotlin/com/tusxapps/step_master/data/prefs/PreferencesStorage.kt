package com.tusxapps.step_master.data.prefs

import com.russhwolf.settings.Settings

class PreferencesStorage(private val settings: Settings) {
    var todaySteps: Int
        get() = settings.getInt(KEY_TODAY_STEPS, 0)
        set(value) {
            settings.putInt(KEY_TODAY_STEPS, value)
        }
    var goalSteps: Int
        get() = settings.getInt(KEY_GOAL_STEPS, 1000)
        set(value) {
            settings.putInt(KEY_GOAL_STEPS, value)
        }

    var todayCalories: Int
        get() = settings.getInt(KEY_TODAY_CALORIES, 0)
        set(value) {
            settings.putInt(KEY_TODAY_CALORIES, value)
        }
    var goalCalories: Int
        get() = settings.getInt(KEY_GOAL_CALORIES, 300)
        set(value) {
            settings.putInt(KEY_GOAL_CALORIES, value)
        }

    var todayActiveTime: Int
        get() = settings.getInt(KEY_TODAY_ACTIVE_TIME, 0)
        set(value) {
            settings.putInt(KEY_TODAY_ACTIVE_TIME, value)
        }
    var goalActiveTime: Int
        get() = settings.getInt(KEY_GOAL_ACTIVE_TIME, 180)
        set(value) {
            settings.putInt(KEY_GOAL_ACTIVE_TIME, value)
        }

    private companion object {
        const val KEY_TODAY_STEPS = "today_steps"
        const val KEY_GOAL_STEPS = "goal_steps"

        const val KEY_TODAY_CALORIES = "today_calories"
        const val KEY_GOAL_CALORIES = "goal_calories"

        const val KEY_TODAY_ACTIVE_TIME = "today_active_time"
        const val KEY_GOAL_ACTIVE_TIME = "goal_active_time"
    }
}
