package com.lyelll.ldxplayer.utils

import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.files.FileHandle
import java.io.ByteArrayOutputStream
import java.io.InputStream
//
///**
// * GIF文件解码器
// * 支持解析GIF文件的基本信息和帧数据
// */
//class GifDecoder {
//    private var width: Int = 0
//    private var height: Int = 0
//    private var frameCount: Int = 0
//    private val frames = mutableListOf<Frame>()
//
//    /**
//     * 读取GIF文件
//     */
//    fun read(fileHandle: FileHandle): Boolean {
//        val inputStream = fileHandle.read()
//        try {
//        try {
//            // 读取GIF文件头
//            val header = ByteArray(6)
//            inputStream.read(header)
//            if (!isGIF(header)) {
//                return false
//            }
//
//            // 读取逻辑屏幕描述符
//            val lsd = ByteArray(7)
//            inputStream.read(lsd)
//            width = (lsd[1].toInt() and 0xFF) or ((lsd[2].toInt() and 0xFF) shl 8)
//            height = (lsd[3].toInt() and 0xFF) or ((lsd[4].toInt() and 0xFF) shl 8)
//
//            // 是否有全局颜色表
//            val hasGlobalColorTable = (lsd[4].toInt() and 0x80) != 0
//            val globalColorTableSize = 1 shl ((lsd[4].toInt() and 0x07) + 1)
//
//            // 读取全局颜色表
//            val globalColorTable = if (hasGlobalColorTable) {
//                ByteArray(globalColorTableSize * 3).also { inputStream.read(it) }
//            } else null
//
//            // 读取图像数据块
//            while (true) {
//                val blockType = inputStream.read()
//                when (blockType) {
//                    0x2C -> { // 图像描述符
//                        readImageBlock(inputStream, globalColorTable)
//                    }
//                    0x21 -> { // 扩展块
//                        val extensionType = inputStream.read()
//                        when (extensionType) {
//                            0xF9 -> { // 图形控制扩展
//                                readGraphicControlExtension(inputStream)
//                            }
//                            else -> { // 其他扩展块
//                                skipBlock(inputStream)
//                            }
//                        }
//                    }
//                    0x3B -> { // 文件结尾
//                        break
//                    }
//                    -1 -> { // 文件结束
//                        break
//                    }
//                }
//            }
//
//            frameCount = frames.size
//            return true
//        } catch (e: Exception) {
//            e.printStackTrace()
//            return false
//        }
//    }
//
//    /**
//     * 获取帧数
//     */
//    fun getFrameCount(): Int = frameCount
//
//    /**
//     * 获取指定帧的延迟时间（毫秒）
//     */
//    fun getDelay(frameIndex: Int): Int {
//        return frames.getOrNull(frameIndex)?.delay ?: 0
//    }
//
//    /**
//     * 获取指定帧的图像数据
//     */
//    fun getFrame(frameIndex: Int): Pixmap? {
//        return frames.getOrNull(frameIndex)?.pixmap
//    }
//
//    /**
//     * 检查是否为GIF文件
//     */
//    private fun isGIF(header: ByteArray): Boolean {
//        return header[0].toInt() == 'G'.code &&
//               header[1].toInt() == 'I'.code &&
//               header[2].toInt() == 'F'.code &&
//               header[3].toInt() == '8'.code &&
//               (header[4].toInt() == '7'.code || header[4].toInt() == '9'.code) &&
//               header[5].toInt() == 'a'.code
//    }
//
//    /**
//     * 读取图形控制扩展块
//     */
//    private fun readGraphicControlExtension(inputStream: InputStream) {
//        val size = inputStream.read()
//        val flags = inputStream.read()
//        val delay = inputStream.read() or (inputStream.read() shl 8)
//        val transparentColorIndex = inputStream.read()
//        inputStream.skip(1) // 块结束标记
//
//        // 创建新帧
//        frames.add(Frame(delay * 10)) // 延迟时间转换为毫秒
//    }
//
//    /**
//     * 读取图像数据块
//     */
//    private fun readImageBlock(inputStream: InputStream, globalColorTable: ByteArray?) {
//        // 读取图像描述符
//        val descriptor = ByteArray(9)
//        inputStream.read(descriptor)
//
//        val left = (descriptor[0].toInt() and 0xFF) or ((descriptor[1].toInt() and 0xFF) shl 8)
//        val top = (descriptor[2].toInt() and 0xFF) or ((descriptor[3].toInt() and 0xFF) shl 8)
//        val width = (descriptor[4].toInt() and 0xFF) or ((descriptor[5].toInt() and 0xFF) shl 8)
//        val height = (descriptor[6].toInt() and 0xFF) or ((descriptor[7].toInt() and 0xFF) shl 8)
//
//        // 是否有局部颜色表
//        val hasLocalColorTable = (descriptor[8].toInt() and 0x80) != 0
//        val localColorTableSize = 1 shl ((descriptor[8].toInt() and 0x07) + 1)
//
//        // 读取局部颜色表
//        val colorTable = if (hasLocalColorTable) {
//            ByteArray(localColorTableSize * 3).also { inputStream.read(it) }
//        } else globalColorTable
//
//        // 读取图像数据
//        val lzwMinCodeSize = inputStream.read()
//        val imageData = readDataBlocks(inputStream)
//
//        // 解压缩图像数据
//        val pixels = decompressLZW(imageData, lzwMinCodeSize)
//
//        // 创建Pixmap
//        val pixmap = Pixmap(width, height, Pixmap.Format.RGBA8888)
//        var index = 0
//        for (y in 0 until height) {
//            for (x in 0 until width) {
//                val colorIndex = pixels[index++].toInt() and 0xFF
//                if (colorTable != null && colorIndex * 3 + 2 < colorTable.size) {
//                    val r = colorTable[colorIndex * 3].toInt() and 0xFF
//                    val g = colorTable[colorIndex * 3 + 1].toInt() and 0xFF
//                    val b = colorTable[colorIndex * 3 + 2].toInt() and 0xFF
//                    val color = (0xFF shl 24) or (r shl 16) or (g shl 8) or b
//                    pixmap.drawPixel(x, height - 1 - y, color)
//                }
//            }
//        }
//
//        // 更新当前帧的图像数据
//        frames.lastOrNull()?.pixmap = pixmap
//    }
//
//    /**
//     * 读取数据块
//     */
//    private fun readDataBlocks(inputStream: InputStream): ByteArray {
//        val baos = ByteArrayOutputStream()
//        while (true) {
//            val blockSize = inputStream.read()
//            if (blockSize <= 0) break
//
//            val block = ByteArray(blockSize)
//            inputStream.read(block)
//            baos.write(block)
//        }
//        return baos.toByteArray()
//    }
//
//    /**
//     * 跳过数据块
//     */
//    private fun skipBlock(inputStream: InputStream) {
//        while (true) {
//            val blockSize = inputStream.read()
//            if (blockSize <= 0) break
//            inputStream.skip(blockSize.toLong())
//        }
//    }
//
//    /**
//     * LZW解压缩
//     */
//    private fun decompressLZW(data: ByteArray, lzwMinCodeSize: Int): ByteArray {
//        // 简单实现：直接返回原始数据
//        // 实际应用中需要实现完整的LZW解压缩算法
//        return data
//    }
//
//    /**
//     * 帧数据类
//     */
//    private data class Frame(
//        val delay: Int,
//        var pixmap: Pixmap? = null
//    )
//}
