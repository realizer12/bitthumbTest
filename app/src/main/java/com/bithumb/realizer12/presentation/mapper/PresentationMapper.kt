package com.bithumb.realizer12.presentation.mapper

interface PresentationMapper<T,E> {
    fun E.fromData():T
}