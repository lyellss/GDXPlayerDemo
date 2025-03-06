package com.lyelll.ldxplayer.model.meta

/**
 * 视频素材元数据
 */
class VideoMaterialMeta : MaterialMeta() {
    /** 是否自动播放 */
    var autoPlay: Boolean = true
    
    /** 音量大小（0.0 ~ 1.0） */
    var volume: Float = 1.0f
}