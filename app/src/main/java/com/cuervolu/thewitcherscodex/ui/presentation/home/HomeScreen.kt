package com.cuervolu.thewitcherscodex.ui.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.cuervolu.thewitcherscodex.R
import com.cuervolu.thewitcherscodex.domain.model.StreamsData
import com.cuervolu.thewitcherscodex.ui.presentation.Dimens.MediumPadding1
import com.cuervolu.thewitcherscodex.ui.presentation.common.StreamsList
import com.cuervolu.thewitcherscodex.ui.theme.CTA
import com.cuervolu.thewitcherscodex.ui.theme.Typography


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    streams: LazyPagingItems<StreamsData>, state: HomeState,
    event: (HomeEvent) -> Unit,
    navigateToSearch: () -> Unit,
    navigateToDetails: (StreamsData) -> Unit
) {

    val titles by remember {
        derivedStateOf {
            if (streams.itemCount > 10) {
                streams.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MediumPadding1)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        val text = buildAnnotatedString {
            withStyle(style = Typography.headlineLarge.toParagraphStyle()) {
                append("Your ")
            }
            withStyle(style = Typography.headlineLarge.toSpanStyle().copy(color = CTA)) {
                append("Witcher 3")
            }
            withStyle(style = Typography.headlineLarge.toParagraphStyle()) {
                append(" Companion App")
            }
        }

        Text(
            text = text,
            modifier = Modifier.padding(horizontal = MediumPadding1),
            fontStyle = Typography.headlineLarge.fontStyle,
            fontWeight = Typography.headlineLarge.fontWeight,
            letterSpacing = Typography.headlineLarge.letterSpacing,
            lineHeight = Typography.headlineLarge.lineHeight,
            color = Typography.headlineLarge.color,
            fontFamily = Typography.headlineLarge.fontFamily,
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        StreamsList(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            streams = streams,
            onClick = navigateToDetails
        )
    }
}

