package com.tusxapps.step_master.data.prefs

import com.russhwolf.settings.Settings

class PreferencesStorage(private val settings: Settings) {
    var bodyFat: Float?
        get() = settings.getFloatOrNull(KEY_BODY_FAT)
        set(value) {
            value?.let {
                settings.putFloat(KEY_BODY_FAT, it)
            }
        }
    var bodyHeight: Float?
        get() = settings.getFloatOrNull(KEY_BODY_HEIGHT)
        set(value) {
            value?.let {
                settings.putFloat(KEY_BODY_HEIGHT, it)
            }
        }
    var muscleWeight: Float?
        get() = settings.getFloatOrNull(KEY_MUSCLE_WEIGHT)
        set(value) {
            value?.let {
                settings.putFloat(KEY_MUSCLE_WEIGHT, it)
            }
        }
    var bodyWeight: Float?
        get() = settings.getFloatOrNull(KEY_BODY_WEIGHT)
        set(value) {
            value?.let {
                settings.putFloat(KEY_BODY_WEIGHT, it)
            }
        }
    var glassCount: Int
        get() = settings.getInt(KEY_GLASS_COUNT, 0)
        set(value) {
            settings.putInt(KEY_GLASS_COUNT, value)
        }

    var todaySteps: Int
        get() = settings.getInt(KEY_TODAY_STEPS, 0)
        set(value) {
            settings.putInt(KEY_TODAY_STEPS, value)
        }
    var goalSteps: Int
        get() = settings.getInt(KEY_GOAL_STEPS, 15000)
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

    var todayDistance: Int
        get() = settings.getInt(KEY_TODAY_DISTANCE, 0)
        set(value) {
            settings.putInt(KEY_TODAY_DISTANCE, value)
        }

    var isAuthorized: Boolean
        get() = settings.getBoolean(KEY_IS_AUTHORIZED, false)
        set(value) {
            settings.putBoolean(KEY_IS_AUTHORIZED, value)
        }

    private companion object {
        const val KEY_TODAY_STEPS = "today_steps"
        const val KEY_GOAL_STEPS = "goal_steps"

        const val KEY_TODAY_CALORIES = "today_calories"
        const val KEY_GOAL_CALORIES = "goal_calories"

        const val KEY_TODAY_ACTIVE_TIME = "today_active_time"
        const val KEY_GOAL_ACTIVE_TIME = "goal_active_time"
        const val KEY_TODAY_DISTANCE = "today_distance"

        const val KEY_IS_AUTHORIZED = "is_authorized"

        const val KEY_GLASS_COUNT = "glass_count"
        const val KEY_BODY_WEIGHT = "body_weight"
        const val KEY_MUSCLE_WEIGHT = "muscle_weight"
        const val KEY_BODY_HEIGHT = "body_height"
        const val KEY_BODY_FAT = "body_fat"
    }
}
