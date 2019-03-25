package com.vasanth.newsapp.di.module.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vasanth.newsapp.di.utility.ViewModelFactory
import com.vasanth.presentation.viewmodel.NewsListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)