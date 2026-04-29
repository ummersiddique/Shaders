# Shaders Project

This project demonstrates the use of AGSL (Android Graphics Shading Language) in Jetpack Compose to create dynamic and efficient visual effects.

## Project Structure

- **`:app`**: The main Android application module that showcases the shaders.
- **`:shaders`**: A library module containing the shader sources and the `ShaderComponent` Composable.

## Features

- **`ShaderComponent`**: A reusable Composable that applies AGSL shaders to its background using `graphicsLayer` and `RenderEffect`.
- **Dynamic Switching**: The app allows real-time switching between different shader effects.
- **Time-based Animation**: Shaders are updated every frame with a `time` uniform for fluid animations.

## Included Shaders

1.  **ZOOMING**: A hypnotic, infinite-zoom fractal effect.
2.  **DISCO**: Sharp, colorful glowing particles moving across the screen.
3.  **SNOW**: A 3D-depth simulated snow falling effect, optimized for performance.

## How to Use

To use a shader in your Compose UI, simply call the `ShaderComponent`:

```kotlin
ShaderComponent(shaderType = ShaderType.DISCO)
```

## Performance Optimizations

The shaders in this project have been carefully optimized for mobile GPUs by:
- Reducing loop iterations.
- Pre-calculating values outside of heavy loops.
- Using efficient vector math (e.g., `dot` products instead of `length`).
- Limiting expensive operations like `sqrt` and `cos`.

## Source Attribution

- **Snow Shader**: Inspired by [@kamoshika_vrc](https://twitter.com/kamoshika_vrc/status/1495081980278751234).
