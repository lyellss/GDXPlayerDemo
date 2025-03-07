package com.lyelll.ldxplayer.model

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array
//import com.lyelll.ldxplayer.utils.GifDecoder
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile

/**
 * GIF素材
 */
class GifMaterial(path: String) : Material {
    private var frames: Array<TextureRegion>? = null
    private var animation: Animation<TextureRegion>? = null
    private var stateTime: Float = 0f
    private var isPaused: Boolean = false
    private var isLooping: Boolean = true

    init {
        // 加载GIF文件并解析帧
//        val gifDecoder = GifDecoder()
//        if (gifDecoder.read(path.toInternalFile())) {
//             创建帧动画
//            frames = Array<TextureRegion>().apply {
//                for (i in 0 until gifDecoder.frameCount) {
//                    gifDecoder.getFrame(i)?.let { pixmap ->
//                        val texture = Texture(pixmap)
//                        add(TextureRegion(texture))
//                    }
//                }
//            }

            // 设置动画
//            if (frames?.size ?: 0 > 0) {
//                animation = Animation<TextureRegion>(gifDecoder.getDelay(0) / 1000f, frames).apply {
//                    playMode = if (isLooping) Animation.PlayMode.LOOP else Animation.PlayMode.NORMAL
//                }
//            }
        }
//        gifDecoder.dispose()
//    }

    override fun render(batch: SpriteBatch, x: Float, y: Float, width: Float, height: Float) {
        animation?.getKeyFrame(stateTime)?.let { frame ->
            batch.draw(frame, x, y, width, height)
        }
    }

    override fun update(delta: Float) {
        if (!isPaused) {
            stateTime += delta
        }
    }

    override fun dispose() {
        frames?.forEach { frame ->
            frame.texture.disposeSafely()
        }
        frames?.clear()
        frames = null
        animation = null
    }

    override fun reset() {
        stateTime = 0f
    }

    override fun pause() {
        isPaused = true
    }

    override fun resume() {
        isPaused = false
    }

    override fun getType(): MaterialType = MaterialType.GIF

    /**
     * 设置是否循环播放
     */
    fun setLooping(looping: Boolean) {
        isLooping = looping
        animation?.playMode = if (looping) Animation.PlayMode.LOOP else Animation.PlayMode.NORMAL
    }

    /**
     * 获取当前帧索引
     */
    fun getCurrentFrameIndex(): Int {
        return animation?.getKeyFrameIndex(stateTime) ?: 0
    }

    /**
     * 获取总帧数
     */
    fun getFrameCount(): Int {
        return frames?.size ?: 0
    }
}
