package com.example.data.mapper

import com.example.data.room.CharacterEntity
import com.example.domain.model.CharacterDetails
import com.example.domain.model.CharacterDetailsDTO
import com.example.domain.model.Character

fun CharacterDetailsDTO.toDomain(): CharacterDetails {
    return CharacterDetails(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        image = image,
        created = created,
    )
}

fun CharacterDetails.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        image = image,
        created = created,
        isFavourites = isFavourites
    )
}

fun CharacterEntity.toDomain(): CharacterDetails {
    return CharacterDetails(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        image = image,
        created = created,
        isFavourites = isFavourites
    )
}

fun CharacterEntity.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        image = image,
        species = species
    )
}