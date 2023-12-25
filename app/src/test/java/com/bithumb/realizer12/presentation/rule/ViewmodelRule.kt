package com.bithumb.realizer12.presentation.rule

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description


@ExperimentalCoroutinesApi
class MainCoroutineRule(val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher())
    :TestWatcher(),TestCoroutineScope by TestCoroutineScope(dispatcher){
    override fun starting(description: Description) {
        super.starting(description)
        println("ㅅㅣ작됨")
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        println("끝남")
        Dispatchers.resetMain()
    }

}

