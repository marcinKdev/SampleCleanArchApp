package com.marcin.domain

import io.reactivex.rxjava3.core.SingleTransformer

interface SchedulersProvider {

fun <O : Any>createSingleTransformer() : SingleTransformer<O, O>
}