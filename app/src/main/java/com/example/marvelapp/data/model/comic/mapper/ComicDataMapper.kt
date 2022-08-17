package com.example.marvelapp.data.model.comic.mapper

import com.example.marvelapp.data.model.comic.model.Comic
import com.example.marvelapp.domain.model.comic.model.ComicDomain
import com.example.marvelapp.util.base.GenericMapper

class ComicDataMapper : GenericMapper<Comic, ComicDomain> {

    override fun mapToDomain(type: Comic): ComicDomain {
        return ComicDomain(
            type.id,
            type.title,
            type.description,
            type.thumbnail
        )
    }

    override fun mapFromDomain(type: ComicDomain): Comic {
        return Comic(
            type.id,
            type.title,
            type.description,
            type.thumbnail
        )
    }
}
