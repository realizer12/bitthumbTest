package com.bithumb.realizer12.presentation.util

import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


fun View.clicks(): Flow<Unit> = callbackFlow {
    this@clicks.setOnClickListener {
        trySend(Unit).isSuccess
    }
    awaitClose { this@clicks.setOnClickListener(null) }
}


fun View.setOnClick(
    uiScope: CoroutineScope,
    skipDuration: Long = 0,
    onClick: () -> Unit,
) {
    clicks()
        .throttleFirst(skipDuration)
        .onEach { onClick.invoke() }
        .launchIn(uiScope)
}
fun <T> Flow<T>.throttleFirst(periodMillis: Long): Flow<T> {
    require(periodMillis >= 0) { "-면 안됨" }
    return flow {
        var lastTime = 0L
        collect { value ->
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastTime >= periodMillis) {
                lastTime = currentTime
                emit(value)
            }
        }
    }
}
