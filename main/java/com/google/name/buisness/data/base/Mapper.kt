package com.google.name.buisness.data.base

interface Mapper<F, T> {
    fun from(value: F): T
    fun to(value: T): F
    fun fromList(value: List<F>): List<T>
    fun toList(value: List<T>): List<F>
}