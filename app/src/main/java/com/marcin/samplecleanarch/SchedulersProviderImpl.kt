package com.marcin.samplecleanarch

import com.marcin.domain.SchedulersProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleTransformer
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SchedulersProviderImpl @Inject constructor() : SchedulersProvider {

            override fun <O : Any> createSingleTransformer(): SingleTransformer<O, O> {
                        return SingleTransformer { it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) }
            }
}