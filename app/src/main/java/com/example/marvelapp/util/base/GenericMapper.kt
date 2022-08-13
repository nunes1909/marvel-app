package com.example.marvelapp.util.base

interface GenericMapper<C, E> {
    fun mapFromCached(type: C): E

    fun mapToCached(type: E): C

    fun mapFromCached(entity: List<C?>): List<E?> {
        return entity.map { if (it == null) null else mapFromCached(it) }
    }

    fun mapFromCachedNonNull(entity: List<C>): List<E> {
        return entity.map { mapFromCached(it) }
    }

    fun mapToCached(domain: List<E?>): List<C?> {
        return domain.map { if (it == null) null else mapToCached(it) }
    }

    fun mapToCachedNonNull(domain: List<E>): List<C> {
        return domain.map { mapToCached(it)!! }
    }
}