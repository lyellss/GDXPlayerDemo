package com.lyelll.ldxplayer.model

import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * 素材接口，定义所有可播放素材的基本行为
 */
interface Material {
    /** 渲染素材 */
    fun render(batch: SpriteBatch, x: Float, y: Float, width: Float, height: Float)
    
    /** 更新素材状态 */
    fun update(delta: Float)
    
    /** 释放资源 */
    fun dispose()
    
    /** 重置素材状态 */
    fun reset()
    
    /** 暂停播放 */
    fun pause()
    
    /** 恢复播放 */
    fun resume()
    
    /** 获取素材类型 */
    fun getType(): MaterialType
}

/**
 * 素材类型枚举
 */
enum class MaterialType {
    IMAGE,          // 静态图片
    SCROLLING_IMAGE,// 滚动图片
    TEXT,           // 静态文本
    SCROLLING_TEXT, // 滚动文本
    VIDEO,          // 视频
    PDF,            // PDF文档
    GIF             // GIF动图
}