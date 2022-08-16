package com.example.marvelapp.view.comic.mapper

import com.example.marvelapp.domain.model.comic.model.ComicDomain
import com.example.marvelapp.util.base.GenericMapper
import com.example.marvelapp.view.comic.model.ComicView

class ComicViewMapper : GenericMapper<ComicView, ComicDomain> {

    override fun mapFromCached(type: ComicView): ComicDomain {
        return ComicDomain(
            type.id,
            type.title,
            type.description,
            type.thumbnail
        )
    }

    override fun mapToCached(type: ComicDomain): ComicView {
        return ComicView(
            type.id,
            type.title,
            type.description,
            type.thumbnail
        )
    }
}