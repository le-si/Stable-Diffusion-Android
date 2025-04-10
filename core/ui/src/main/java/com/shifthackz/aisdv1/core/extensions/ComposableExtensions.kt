package com.shifthackz.aisdv1.core.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.shifthackz.aisdv1.core.common.extensions.EmptyLambda

@Composable
fun Modifier.gesturesDisabled() = clickable(
    indication = null,
    interactionSource = remember { MutableInteractionSource() },
    onClick = EmptyLambda
)

@Composable
fun measureTextWidth(text: String, style: TextStyle = LocalTextStyle.current): Dp {
    val textMeasurer = rememberTextMeasurer()
    val widthInPixels = textMeasurer.measure(text, style).size.width
    return with(LocalDensity.current) { widthInPixels.toDp() }
}

@Composable
fun Modifier.fadedEdge(color: Color = MaterialTheme.colorScheme.background, gradientOffset: Dp = 0.dp) =
    graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
        .drawWithContent {
            drawContent()
            drawRect(
                brush = Brush.verticalGradient(
                    startY = gradientOffset.toPx(),
                    colors = listOf(
                        color,
                        Color.Transparent,
                    )
                ),
                blendMode = BlendMode.DstIn,
            )
        }
