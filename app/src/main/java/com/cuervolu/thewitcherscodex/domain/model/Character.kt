package com.cuervolu.thewitcherscodex.domain.model

data class Character(
    val name: String = "",
    val alias: List<String> = emptyList(),
    val basic_information: BasicInformation? = null,
    val personal_information: PersonalInformation? = null,
    val image_url: String = "",
    val description: String = "",
    val age: String? = null,
    val status: String? = null
)

data class BasicInformation(
    val hair_color: String? = null,
    val eye_color: String? = null,
    val skin: String? = null,
    val race: String? = null,
    val gender: String? = null,
    val height: String? = null,
    val nationality: String? = null,
    val born: String? = null
)

data class PersonalInformation(
    val titles: List<String>? = null,
    val profession: String? = null,
    val affiliations: List<String>? = null,
    val abilities: List<String>? = null,
    val family: Family? = null,
    val mage: String? = null
)

data class Family(
    val parents: Parents? = null,
    val partners: List<String>? = null,
    val children: List<String>? = null,
    val house: String? = null,
    val adoptiveParents: Parents? = null,
    val relatives: List<String>? = null
)

data class Parents(
    val mother: String? = null,
    val father: String? = null
)
