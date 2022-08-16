package com.example.marvelapp.data.model.comic.mapper

import com.example.marvelapp.data.model.comic.model.Comic
import com.example.marvelapp.domain.comic.model.ComicDomain
import com.example.marvelapp.util.base.GenericMapper

class ComicDataMapper : GenericMapper<Comic, ComicDomain> {

    override fun mapFromCached(type: Comic): ComicDomain {
        return ComicDomain(
            type.id,
            type.title,
            type.description,
            type.thumbnail
        )
    }

    override fun mapToCached(type: ComicDomain): Comic {
        return Comic(
            type.id,
            type.title,
            type.description,
            type.thumbnail
        )
    }
}
