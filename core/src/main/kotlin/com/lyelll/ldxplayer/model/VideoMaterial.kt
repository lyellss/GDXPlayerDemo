package com.lyelll.ldxplayer.model

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.video.VideoPlayer
import com.badlogic.gdx.video.VideoPlayerCreator
import com.lyelll.ldxplayer.model.meta.VideoMaterialMeta
import ktx.assets.toInternalFile

/**
 * 视频素材
 */
class VideoMaterial(private val meta: VideoMaterialMeta) : Material {
    private var videoPlayer: VideoPlayer? = null
    private var isPaused: Boolean = false

    init {
        // 创建视频播放器
        videoPlayer = VideoPlayerCreator.createVideoPlayer().apply {
            setOnCompletionListener {
                if (meta.isLooping) {
                    // 循环播放
                    play(meta.path.toInternalFile())
                }
            }
            setOnVideoSizeListener { width, height ->
                // 可以在这里处理视频尺寸变化
            }
            setVolume(meta.volume)
            if (meta.autoPlay) {
                play(meta.path.toInternalFile())
            }
        }
    }

    override fun render(batch: SpriteBatch, x: Float, y: Float, width: Float, height: Float) {
        videoPlayer?.let { player ->
//            if (player.texture != null) {
//                batch.draw(player.texture, x, y, width, height)
//            }
        }
    }

    override fun update(delta: Float) {
        if (!isPaused) {
//            videoPlayer?.update()
        }
    }

    override fun dispose() {
        videoPlayer?.dispose()
        videoPlayer = null
    }

    override fun reset() {
        videoPlayer?.let { player ->
//            val currentFile = player.currentFile
//            if (currentFile != null) {
//                player.play(currentFile)
//            }
        }
    }

    override fun pause() {
        isPaused = true
        videoPlayer?.pause()
    }

    override fun resume() {
        isPaused = false
        videoPlayer?.resume()
    }

    override fun getType(): MaterialType = MaterialType.VIDEO

    /**
     * 获取视频是否正在播放
     */
    fun isPlaying(): Boolean = videoPlayer?.isPlaying ?: false

    /**
     * 获取视频当前播放位置（秒）
     */
//    fun getCurrentPosition(): Float = videoPlayer?.currentPosition ?: 0f

    /**
     * 获取视频总时长（秒）
     */
//    fun getDuration(): Float = videoPlayer?.duration ?: 0f

    /**
     * 设置播放位置（秒）
     */
    fun seekTo(position: Float) {
//        videoPlayer?.seekTo(position)
    }

    /**
     * 设置音量大小（0.0 ~ 1.0）
     */
    fun setVolume(volume: Float) {
        videoPlayer?.setVolume(volume)
    }
}
