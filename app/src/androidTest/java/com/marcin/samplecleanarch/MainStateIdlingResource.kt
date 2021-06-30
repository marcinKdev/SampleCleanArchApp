package com.marcin.samplecleanarch

import androidx.lifecycle.Observer
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback
import com.marcin.domain.MainScreenState

class MainScreenStateIdlingResource() : IdlingResource, Observer<MainScreenState> {

    private var idle = false
    private lateinit var resourceCallback: ResourceCallback

    override fun getName(): String {
        return MainScreenStateIdlingResource::class.java.simpleName
    }

    override fun isIdleNow(): Boolean {
        if (idle) { // notify callback that the app is on the way to idle state
            resourceCallback.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(resourceCallback: ResourceCallback) {
        this.resourceCallback = resourceCallback
    }

    override fun onChanged(state: MainScreenState?) {
        idle = state?.repositories?.isNotEmpty() == true
    }
}
