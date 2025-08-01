package com.example.sahhamarket.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.serialization.decodeArguments
import kotlinx.serialization.KSerializer
import kotlin.collections.iterator
import kotlin.reflect.KClass

fun <T : Any> NavBackStackEntry.toRoute(serializersByRoute: Map<KClass<out T>, KSerializer<out T>>): T? {
    val destination = destination
    for ((route, serializer) in serializersByRoute) {
        if (destination.hasRoute(route)) {
            return toRoute(serializer)
        }
    }
    return null
}

@SuppressLint("RestrictedApi")
private fun <T> NavBackStackEntry.toRoute(serializer: KSerializer<T>): T {
    val bundle = arguments ?: Bundle()
    val typeMap = destination.arguments.mapValues { it.value.type }
    return serializer.decodeArguments(bundle, typeMap)
}