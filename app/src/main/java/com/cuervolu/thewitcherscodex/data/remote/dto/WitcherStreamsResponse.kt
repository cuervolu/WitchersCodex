package com.cuervolu.thewitcherscodex.data.remote.dto

import com.cuervolu.thewitcherscodex.domain.model.Pagination
import com.cuervolu.thewitcherscodex.domain.model.StreamsData

data class WitcherStreamsResponse(
    val `data`: List<StreamsData>,
    val pagination: Pagination
)