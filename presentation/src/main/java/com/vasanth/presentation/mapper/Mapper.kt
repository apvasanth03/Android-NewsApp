package com.vasanth.presentation.mapper

interface Mapper<V, D> {

    fun mapToView(type: D): V

}