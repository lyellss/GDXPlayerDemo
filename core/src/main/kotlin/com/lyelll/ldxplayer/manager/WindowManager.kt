package com.lyelll.ldxplayer.manager

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.lyelll.ldxplayer.window.Window
import com.lyelll.ldxplayer.model.Material

/**
 * 窗口管理器，负责管理所有窗口的生命周期
 */
class WindowManager {
    private val windows = mutableListOf<Window>()
    
    /**
     * 创建新窗口
     */
    fun createWindow(x: Float, y: Float, width: Float, height: Float, zIndex: Int = 0): Window {
        val window = Window(x, y, width, height, zIndex)
        windows.add(window)
        sortWindows()
        return window
    }
    
    /**
     * 删除窗口
     */
    fun removeWindow(window: Window) {
        window.dispose()
        windows.remove(window)
    }
    
    /**
     * 根据索引获取窗口
     */
    fun getWindow(index: Int): Window? = windows.getOrNull(index)
    
    /**
     * 获取所有窗口
     */
    fun getAllWindows(): List<Window> = windows.toList()
    
    /**
     * 更新窗口层级
     */
    fun updateWindowZIndex(window: Window, newZIndex: Int) {
        window.setZIndex(newZIndex)
        sortWindows()
    }
    
    /**
     * 根据层级排序窗口
     */
    private fun sortWindows() {
        windows.sortBy { it.zIndex }
    }
    
    /**
     * 渲染所有窗口
     */
    fun render(batch: SpriteBatch, delta: Float) {
        windows.forEach { window ->
            window.render(batch, delta)
        }
    }
    
    /**
     * 释放所有窗口资源
     */
    fun dispose() {
        windows.forEach { it.dispose() }
        windows.clear()
    }
}