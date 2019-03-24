package com.vasanth.cache.mapper

/**
 * Interface defines generic method for mapping "cache model" to "data model and vise versa"
 *
 * @author Vasanth
 */
interface CacheMapper<C, E> {

    fun mapFromCached(cache: C): E

    fun mapToCached(entity: E): C

}