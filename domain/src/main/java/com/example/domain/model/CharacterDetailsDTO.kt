package com.example.domain.model

data class CharacterDetailsDTO(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val created: String,
) {
    fun toCharacterDetails() : CharacterDetails {
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
}
