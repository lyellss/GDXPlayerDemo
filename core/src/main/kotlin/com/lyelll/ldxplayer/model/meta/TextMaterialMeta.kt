package com.lyelll.ldxplayer.model.meta

/**
 * 文本素材元数据
 */
open class TextMaterialMeta : MaterialMeta() {
    /** 文本内容 */
    var text: String = ""
    
    /** 字体大小 */
    var fontSize: Float = 24f
    
    /** 文本颜色 */
    var color: Int = 0xFFFFFFFF.toInt()
}