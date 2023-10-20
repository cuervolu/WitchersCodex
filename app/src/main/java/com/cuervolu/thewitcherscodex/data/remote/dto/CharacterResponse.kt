package com.cuervolu.thewitcherscodex.data.remote.dto

import com.cuervolu.thewitcherscodex.domain.model.Character

data class CharacterResponse(
    val `data`: List<Character>,
)
