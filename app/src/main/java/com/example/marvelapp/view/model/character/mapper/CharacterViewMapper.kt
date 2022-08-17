package com.example.marvelapp.view.model.character.mapper

import com.example.marvelapp.domain.model.character.model.CharacterDomain
import com.example.marvelapp.util.base.GenericMapper
import com.example.marvelapp.view.model.character.model.CharacterView

class CharacterViewMapper : GenericMapper<CharacterView, CharacterDomain> {

    override fun mapFromCached(type: CharacterView): CharacterDomain {
        return CharacterDomain(
            type.id,
            type.name,
            type.description,
            type.thumbnail,
        )
    }

    override fun mapToCached(type: CharacterDomain): CharacterView {
        return CharacterView(
            type.id,
            type.name,
            type.description,
            type.thumbnail,
        )
    }
}