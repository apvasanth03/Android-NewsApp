package com.vasanth.remote.test.factory

import com.vasanth.remote.model.SourceModel

object SourceModelFactory {

    fun makeSourceModel(): SourceModel{
        return SourceModel(DataFactory.randomString(), DataFactory.randomString())
    }

}