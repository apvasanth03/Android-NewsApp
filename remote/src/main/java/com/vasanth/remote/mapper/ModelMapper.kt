package com.vasanth.remote.mapper

/**
 * Interface defines generic method for mapping "remote model" to "data model"
 *
 * @author Vasanth
 */
interface ModelMapper<M, E> {

    fun mapFromModel(model: M): E

}