package com.lyelll.ldxplayer.model

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.lyelll.ldxplayer.model.meta.ScrollingTextMaterialMeta

/**
 * 滚动文本素材
 */
class ScrollingTextMaterial(meta: ScrollingTextMaterialMeta) : TextMaterial(meta) {
    private var textX: Float = 0f
    private var textWidth: Float = 0f
    private var containerWidth: Float = 0f
    private var isPaused: Boolean = false
    private val speed: Float = meta.speed
    
    override fun render(batch: SpriteBatch, x: Float, y: Float, width: Float, height: Float) {
        if (textWidth == 0f) {
            containerWidth = width
            textX = width // 从屏幕右侧开始
        }
        
        // 绘制当前文本
        super.render(batch, textX, y, width, height)
        // 绘制下一个文本
        super.render(batch, textX + containerWidth, y, width, height)
    }
    
    override fun update(delta: Float) {
        if (isPaused) return
        
        // 更新文本位置
        textX -= speed * delta
        
        // 如果文本移出屏幕左侧，重置到右侧
        if (textX < -containerWidth) {
            textX = 0f
        }
    }
    
    override fun reset() {
        textX = containerWidth
    }
    
    override fun pause() {
        isPaused = true
    }
    
    override fun resume() {
        isPaused = false
    }
    
    override fun getType(): MaterialType = MaterialType.SCROLLING_TEXT
}