package com.bithumb.realizer12.presentation.mapper

/**
 * Create Date: 2023/11/20
 *
 *
 * ui 레이어에서 사용되는  mapper
 *
 * @author LeeDongHun
 *
 **/
interface PresentationMapper<T,E> {
    fun E.fromData():T
}