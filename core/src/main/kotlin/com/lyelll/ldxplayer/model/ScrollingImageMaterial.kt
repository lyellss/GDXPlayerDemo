package com.lyelll.ldxplayer.model

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.lyelll.ldxplayer.model.meta.ScrollingImageMaterialMeta

/**
 * 滚动图片素材
 */
class ScrollingImageMaterial(meta: ScrollingImageMaterialMeta) : ImageMaterial(meta) {
    private var imageX: Float = 0f
    private var imageWidth: Float = 0f
    private var isPaused: Boolean = false
    private val speed: Float = meta.speed
    
    override fun render(batch: SpriteBatch, x: Float, y: Float, width: Float, height: Float) {
        if (imageWidth == 0f) {
            imageWidth = width
            imageX = -width // 从屏幕左侧外开始
        }
        
        // 绘制当前图片
        super.render(batch, imageX, y, width, height)
        // 绘制下一个图片
        super.render(batch, imageX - width, y, width, height)
    }
    
    override fun update(delta: Float) {
        if (isPaused) return
        
        // 更新图片位置
        imageX += speed * delta
        
        // 如果图片移出屏幕右侧，重置到左侧
        if (imageX > Gdx.graphics.width) {
            imageX -= Gdx.graphics.width
        }
    }
    
    override fun reset() {
        imageX = -imageWidth
    }
    
    override fun pause() {
        isPaused = true
    }
    
    override fun resume() {
        isPaused = false
    }
    
    override fun getType(): MaterialType = MaterialType.SCROLLING_IMAGE
}