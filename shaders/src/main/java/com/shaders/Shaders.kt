package com.shaders

object Shaders {

    private const val ZOOMING_SHADER_SRC = """
        uniform float2 size;
        uniform float time;
        uniform shader composable;
    
        float f(float3 p) {
            p.z -= time * 10.;
            float a = p.z * .1;
            p.xy *= mat2(cos(a), sin(a), -sin(a), cos(a));
            return .1 - length(cos(p.xy) + sin(p.yz));
        }
    
        half4 main(float2 fragcoord) {
            float3 d = .5 - float3(fragcoord, 1.0) / size.y;
            float3 p = float3(0);
            for (int i = 0; i < 32; i++) {
              p += f(p) * d;
            }
            return float4((sin(p) + float3(2, 5, 12)) / length(p), 1.0);
        }
"""

     const val DISCO_SHADER_SRC = """
    uniform float2 size;
    uniform float time;
    uniform shader composable;

    float4 main(float2 FC) {
        float2 u = (FC * 2.0 - size) / size.y;
        float3 col = float3(0);
        for (float i=0.0; i<20.0; i++) {
            float2 p = sin(time + i * float2(1.1, 1.5));
            // Sharpened by reducing the offset from 0.005 to 0.0002
            col += (cos(i + float3(0, 2, 4)) + 1.0) * 0.001 / (dot(u - p * 0.8, u - p * 0.8) + 0.0002);
        }
        return float4(col, 1.0);
    }
"""

    const val SNOW_FALLING_SHADER_SRC = """
    uniform float2 size;
    uniform float time;
    uniform shader composable;

    float F(float2 c) {
        return fract(sin(dot(c, float2(12.98, 78.23))) * 43758.54);
    }

    half4 main(float2 FC) {
        float4 o = float4(0);
        float3 R = normalize(float3((FC * 2.0 - size) / size.y, 1.0));
        for (float i = 0.0; i < 30.0; ++i) {
            float I = floor(time * 10.0) + i;
            float d = (I * 0.1 - time) / R.z;
            // Changed "time +" to "F(I.xx * 0.8) - time" to reverse direction to downward
            float2 p = d * R.xy + float2(sin(time + F(I.xx) * 6.28) * 0.3 + F(I.xx * 0.9), F(I.xx * 0.8) - time);
            if (F(I * 0.01 + ceil(p)) < 0.2) {
                o += smoothstep(0.1, 0.0, length(fract(p) - 0.5)) * exp(-d * d * 0.04);
            }
        }
        return o;
    }
"""
}