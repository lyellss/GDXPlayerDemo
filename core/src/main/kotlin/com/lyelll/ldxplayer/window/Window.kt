package com.lyelll.ldxplayer.model

import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * 窗口类，负责管理单个窗口的属性和行为
 */
class Window(
    var x: Float,           // 窗口x坐标
    var y: Float,           // 窗口y坐标
    var width: Float,       // 窗口宽度
    var height: Float,      // 窗口高度
    var zIndex: Int = 0     // 窗口层级
) {
    private var currentMaterial: Material? = null
    private var isVisible: Boolean = true
    
    /**
     * 设置窗口位置
     */
    fun setPosition(x: Float, y: Float) {
        this.x = x
        this.y = y
    }
    
    /**
     * 设置窗口大小
     */
    fun setSize(width: Float, height: Float) {
        this.width = width
        this.height = height
    }
    
    /**
     * 设置窗口层级
     */
    fun setZIndex(zIndex: Int) {
        this.zIndex = zIndex
    }
    
    /**
     * 设置窗口可见性
     */
    fun setVisible(visible: Boolean) {
        isVisible = visible
    }
    
    /**
     * 设置当前播放的素材
     */
    fun setMaterial(material: Material?) {
        currentMaterial?.dispose() // 释放旧素材资源
        currentMaterial = material
    }
    
    /**
     * 获取当前播放的素材
     */
    fun getCurrentMaterial(): Material? = currentMaterial
    
    /**
     * 渲染窗口内容
     */
    fun render(batch: SpriteBatch, delta: Float) {
        if (!isVisible) return
        
        currentMaterial?.let { material ->
            material.update(delta)
            material.render(batch, x, y, width, height)
        }
    }
    
    /**
     * 释放窗口资源
     */
    fun dispose() {
        currentMaterial?.dispose()
        currentMaterial = null
    }
}