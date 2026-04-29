package com.shaders

import android.graphics.RenderEffect
import android.graphics.RuntimeShader
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ShaderComponent(
    shaderType: ShaderType,
    modifier: Modifier = Modifier
) {
    val shader = remember(shaderType) {
        val src = when (shaderType) {
            ShaderType.ZOOMING -> Shaders.ZOOMING_SHADER_SRC
            ShaderType.DISCO -> Shaders.DISCO_SHADER_SRC
            ShaderType.SNOW -> Shaders.SNOW_FALLING_SHADER_SRC
        }
        RuntimeShader(src)
    }

    val timeMs = remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        val startTime = withFrameMillis { it }
        while (true) {
            withFrameMillis { frameTime ->
                timeMs.floatValue = (frameTime - startTime) / 1000f
            }
        }
    }

    Surface(
        modifier = modifier
            .background(color = Color.Black)
            .fillMaxSize()
            .onSizeChanged {
                shader.setFloatUniform("size", it.width.toFloat(), it.height.toFloat())
            }
            .graphicsLayer {
                shader.setFloatUniform("time", timeMs.floatValue)
                renderEffect = RenderEffect
                    .createRuntimeShaderEffect(shader, "composable")
                    .asComposeRenderEffect()
            }
    ) {

    }
}

@Preview
@Composable
private fun ShaderComponentPreview() {
    ShaderComponent(shaderType = ShaderType.DISCO)
}