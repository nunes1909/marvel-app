package com.example.marvelapp.view.model.comic.mapper

import com.example.marvelapp.domain.model.comic.model.ComicDomain
import com.example.marvelapp.util.base.GenericMapper
import com.example.marvelapp.view.model.comic.model.ComicView

class ComicViewMapper : GenericMapper<ComicView, ComicDomain> {

    override fun mapToDomain(type: ComicView): ComicDomain {
        return ComicDomain(
            type.id,
            type.title,
            type.description,
            type.thumbnail
        )
    }

    override fun mapFromDomain(type: ComicDomain): ComicView {
        return ComicView(
            type.id,
            type.title,
            type.description,
            type.thumbnail
        )
    }
}