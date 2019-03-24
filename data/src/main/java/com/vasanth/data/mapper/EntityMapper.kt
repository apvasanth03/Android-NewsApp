package com.vasanth.data.mapper

/**
 * Interface defines generic method for mapping "data model" to "domain model"
 *
 * @author Vasanth
 */
interface EntityMapper<E, D> {

    fun mapFromEntity(entity: E): D

}