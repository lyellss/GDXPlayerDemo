package com.lyelll.ldxplayer.model

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.lyelll.ldxplayer.model.meta.ImageMaterialMeta
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile

/**
 * 静态图片素材
 */
open class ImageMaterial(meta: ImageMaterialMeta) : Material {
    private var texture: Texture? = null
    private var isPaused: Boolean = false

    init {
        // 加载图片资源
        texture = Texture(meta.path.toInternalFile(), true).apply {
            setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        }
    }

    override fun render(batch: SpriteBatch, x: Float, y: Float, width: Float, height: Float) {
        texture?.let { tex ->
            batch.draw(tex, x, y, width, height)
        }
    }

    override fun update(delta: Float) {
        // 静态图片不需要更新
    }

    override fun dispose() {
        texture?.disposeSafely()
        texture = null
    }

    override fun reset() {
        // 静态图片不需要重置
    }

    override fun pause() {
        isPaused = true
    }

    override fun resume() {
        isPaused = false
    }

    override fun getType(): MaterialType = MaterialType.IMAGE
}
