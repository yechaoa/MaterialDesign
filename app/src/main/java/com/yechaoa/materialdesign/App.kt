package com.yechaoa.materialdesign

import android.app.Application
import com.google.android.material.color.DynamicColors

/**
 * GitHub : https://github.com/yechaoa
 * CSDN : http://blog.csdn.net/yechaoa
 *
 * Created by yechao on 2022/5/1.
 * Describe :
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        // apply dynamic color
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}