package com.example.marvelapp.data.model.character.mapper

import com.example.marvelapp.data.model.character.model.Character
import com.example.marvelapp.domain.model.character.model.CharacterDomain
import com.example.marvelapp.util.base.GenericMapper

class CharacterDataMapper : GenericMapper<Character, CharacterDomain> {

    override fun mapFromCached(type: Character): CharacterDomain {
        return CharacterDomain(
            type.id,
            type.name,
            type.description,
            type.thumbnail
        )
    }

    override fun mapToCached(type: CharacterDomain): Character {
        return Character(
            type.id,
            type.name,
            type.description,
            type.thumbnail
        )
    }
}
