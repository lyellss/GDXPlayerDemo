package com.lyelll.ldxplayer.manager

import com.lyelll.ldxplayer.model.*
import com.lyelll.ldxplayer.model.meta.ImageMaterialMeta
import com.lyelll.ldxplayer.model.meta.ScrollingImageMaterialMeta
import com.lyelll.ldxplayer.model.meta.ScrollingTextMaterialMeta
import com.lyelll.ldxplayer.model.meta.TextMaterialMeta
import com.lyelll.ldxplayer.model.meta.VideoMaterialMeta

/**
 * 素材管理器，负责管理所有素材的生命周期和缓存
 */
class MaterialManager {
    private val materialCache = mutableMapOf<String, Material>()

    /**
     * 创建或获取素材
     * @param type 素材类型
     * @param path 素材路径
     * @param params 额外参数，如文本内容、滚动速度等
     */
//    fun createMaterial(meta: MaterialMeta): Material {
//        // 检查缓存中是否存在
//        materialCache[meta.path]?.let { return it }
//
//        // 根据类型创建对应的素材
//        val material = when (meta) {
//            is ImageMaterialMeta -> createImageMaterial(meta)
//            is ScrollingImageMaterialMeta -> createScrollingImageMaterial(meta)
//            is TextMaterialMeta -> createTextMaterial(meta)
//            is ScrollingTextMaterialMeta -> createScrollingTextMaterial(meta)
//            is VideoMaterialMeta -> createVideoMaterial(meta)
//            is PdfMaterialMeta -> createPdfMaterial(meta)
//            is GifMaterialMeta -> createGifMaterial(meta)
//        }
//
//        // 缓存并返回素材
//        materialCache[meta.path] = material
//        return material
//    }

    /**
     * 释放指定素材
     */
    fun disposeMaterial(path: String) {
        materialCache.remove(path)?.dispose()
    }

    /**
     * 释放所有素材
     */
    fun dispose() {
        materialCache.values.forEach { it.dispose() }
        materialCache.clear()
    }

    // 以下是各种素材类型的创建方法，后续会实现具体逻辑
    private fun createImageMaterial(meta: ImageMaterialMeta): Material {
        return ImageMaterial(meta)
    }

    private fun createScrollingImageMaterial(meta: ScrollingImageMaterialMeta): Material {
        return ScrollingImageMaterial(meta)
    }

    private fun createTextMaterial(meta: TextMaterialMeta): Material {
        return TextMaterial(meta)
    }

    private fun createScrollingTextMaterial(meta: ScrollingTextMaterialMeta): Material {
        return ScrollingTextMaterial(meta)
    }

    private fun createVideoMaterial(meta: VideoMaterialMeta): Material {
        return VideoMaterial(meta)
    }

//    private fun createPdfMaterial(meta: PdfMaterialMeta): Material {
//        return PdfMaterial(meta.path)
//    }

//    private fun createGifMaterial(meta: GifMaterialMeta): Material {
//        return GifMaterial(meta.path)
//    }
}
