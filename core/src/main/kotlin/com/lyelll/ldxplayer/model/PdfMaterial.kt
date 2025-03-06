package com.lyelll.ldxplayer.model

import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.rendering.PDFRenderer

/**
 * PDF素材
 */
class PdfMaterial(path: String) : Material {
    private var document: PDDocument? = null
    private var renderer: PDFRenderer? = null
    private var currentTexture: Texture? = null
    private var currentPage: Int = 0
    private var isPaused: Boolean = false
    private var scale: Float = 1f
    
    init {
        // 加载PDF文档
        document = PDDocument.load(path.toInternalFile().file())
        renderer = PDFRenderer(document)
        // 渲染第一页
        renderCurrentPage()
    }
    
    override fun render(batch: SpriteBatch, x: Float, y: Float, width: Float, height: Float) {
        currentTexture?.let { texture ->
            batch.draw(texture, x, y, width * scale, height * scale)
        }
    }
    
    override fun update(delta: Float) {
        // PDF不需要定时更新
    }
    
    override fun dispose() {
        currentTexture?.disposeSafely()
        currentTexture = null
        document?.close()
        document = null
        renderer = null
    }
    
    override fun reset() {
        currentPage = 0
        renderCurrentPage()
    }
    
    override fun pause() {
        isPaused = true
    }
    
    override fun resume() {
        isPaused = false
    }
    
    override fun getType(): MaterialType = MaterialType.PDF
    
    /**
     * 渲染当前页
     */
    private fun renderCurrentPage() {
        currentTexture?.disposeSafely()
        
        document?.let { doc ->
            renderer?.let { pdf ->
                if (currentPage in 0 until doc.numberOfPages) {
                    // 渲染PDF页面到图像
                    val image = pdf.renderImageWithDPI(currentPage, 300f)
                    // 转换为Pixmap
                    val pixmap = Pixmap(image.width, image.height, Pixmap.Format.RGBA8888).apply {
                        for (y in 0 until image.height) {
                            for (x in 0 until image.width) {
                                drawPixel(x, y, image.getRGB(x, y))
                            }
                        }
                    }
                    // 创建纹理
                    currentTexture = Texture(pixmap)
                    pixmap.dispose()
                }
            }
        }
    }
    
    /**
     * 切换到下一页
     */
    fun nextPage(): Boolean {
        document?.let { doc ->
            if (currentPage < doc.numberOfPages - 1) {
                currentPage++
                renderCurrentPage()
                return true
            }
        }
        return false
    }
    
    /**
     * 切换到上一页
     */
    fun previousPage(): Boolean {
        if (currentPage > 0) {
            currentPage--
            renderCurrentPage()
            return true
        }
        return false
    }
    
    /**
     * 跳转到指定页
     */
    fun goToPage(pageNumber: Int): Boolean {
        document?.let { doc ->
            if (pageNumber in 0 until doc.numberOfPages) {
                currentPage = pageNumber
                renderCurrentPage()
                return true
            }
        }
        return false
    }
    
    /**
     * 获取当前页码
     */
    fun getCurrentPage(): Int = currentPage
    
    /**
     * 获取总页数
     */
    fun getPageCount(): Int = document?.numberOfPages ?: 0
    
    /**
     * 设置缩放比例
     */
    fun setScale(newScale: Float) {
        scale = newScale.coerceIn(0.1f, 5f)
        renderCurrentPage()
    }
    
    /**
     * 获取当前缩放比例
     */
    fun getScale(): Float = scale
}