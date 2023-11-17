package com.bithumb.test.presentation.mapper

interface PresentationMapper<T,E> {
    fun E.fromData():T
}