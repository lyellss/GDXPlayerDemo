package com.lyelll.ldxplayer.model

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.lyelll.ldxplayer.model.meta.TextMaterialMeta
import ktx.assets.disposeSafely

/**
 * 静态文本素材
 */
class TextMaterial(val meta: TextMaterialMeta) : Material {
    private var font: BitmapFont? = null
    private var glyphLayout: GlyphLayout? = null
    private var isPaused: Boolean = false

    init {
        font = BitmapFont().apply {
            data.setScale(meta.fontSize / data.fontFile.fontSize)
            setColor(meta.color)
        }
        glyphLayout = GlyphLayout(font, meta.text)
    }

    override fun render(batch: SpriteBatch, x: Float, y: Float, width: Float, height: Float) {
        font?.let { f ->
            glyphLayout?.let { layout ->
                // 计算文本居中位置
                val textX = x + (width - layout.width) / 2
                val textY = y + (height + layout.height) / 2
                f.draw(batch, meta.text, textX, textY)
            }
        }
    }

    override fun update(delta: Float) {
        // 静态文本不需要更新
    }

    override fun dispose() {
        font?.disposeSafely()
        font = null
        glyphLayout = null
    }

    override fun reset() {
        // 静态文本不需要重置
    }

    override fun pause() {
        isPaused = true
    }

    override fun resume() {
        isPaused = false
    }

    override fun getType(): MaterialType = MaterialType.TEXT
}
