package com.lyelll.ldxplayer

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter.Linear
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.async.KtxAsync
import ktx.graphics.use

class Main : KtxGame<KtxScreen>() {
    override fun create() {
        KtxAsync.initiate()

        addScreen(FirstScreen())
        setScreen<FirstScreen>()
    }
}

class FirstScreen : KtxScreen {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    private var image1 = Texture("11.jpg".toInternalFile(), true).apply {
        setFilter(Linear, Linear)
    }

    private var image2 = Texture("22.jpg".toInternalFile(), true).apply {
        setFilter(Linear, Linear)
    }

    private var richTextImage1 = Texture("44.png".toInternalFile(), true).apply {
        setFilter(Linear, Linear)
    }

    private var richTextImage2 = Texture("44.png".toInternalFile(), true).apply {
        setFilter(Linear, Linear)
    }

    private var textImage = Texture("33.png".toInternalFile(), true).apply {
        setFilter(Linear, Linear)
    }

    private var tempImage: Texture = richTextImage1

    private val batch = SpriteBatch()

    // 添加变量来跟踪图片位置
    private var translateX = 0f // 从屏幕左侧外开始
    private val speed = 800f // 每秒移动的像素数

    private var sleepTime: Long = 10000

    private var renderType = RenderType.IMAGE

    override fun show() {
        val size = RenderType.entries.size
        var index = 0
        ioScope.launch {
            while (true) {
                if (index == size) {
                    index = 0
                }
                renderType = RenderType.entries[index]
                index++
                delay(sleepTime)
            }
        }
    }

    override fun resize(width: Int, height: Int) {

    }

    override fun render(delta: Float) {
        clearScreen(red = 0f, green = 0f, blue = 0f)
        when (renderType) {
            RenderType.IMAGE -> {
                renderImage(delta)
            }

            RenderType.TEXT -> {
                renderText(delta)
            }

            RenderType.RICH_TEXT -> {
                renderRich(delta)
            }
        }
        // 更新图片位置
        translateX += speed * delta
    }

    override fun dispose() {
        ioScope.cancel()
        richTextImage1.disposeSafely()
        batch.disposeSafely()
    }

    private fun renderVideo(delta: Float) {
//        videoPlayer?.let { player ->
//            if (player.texture != null) {
//                batch.draw(player.texture, 0f, 0f, 800f, 540f)
//            }
//        }
    }

    private fun renderImage(delta: Float) {
        // 如果图片移出屏幕右侧，重置到左侧
        if (translateX > Gdx.graphics.width) { // 假设屏幕宽度为800像素
            translateX -= Gdx.graphics.width
            tempImage = image1
            image1 = image2
            image2 = tempImage
        }
        batch.use {
            it.draw(image1, translateX, 0f)
            it.draw(image2, translateX - richTextImage1.width, 0f)
        }
    }


    private fun renderRich(delta: Float) {
        // 如果图片移出屏幕右侧，重置到左侧
        if (translateX > Gdx.graphics.width) { // 假设屏幕宽度为800像素
            translateX -= Gdx.graphics.width
            tempImage = richTextImage1
            richTextImage1 = richTextImage2
            richTextImage2 = tempImage
        }
        batch.use {
            it.draw(richTextImage1, translateX, 0f)
            it.draw(richTextImage2, translateX - richTextImage1.width, 0f)
        }
    }

    private fun renderText(delta: Float) {
        // 如果图片移出屏幕右侧，重置到左侧
        if (translateX > Gdx.graphics.width) { // 假设屏幕宽度为800像素
            translateX -= Gdx.graphics.width
            tempImage = richTextImage1
            richTextImage1 = richTextImage2
            richTextImage2 = tempImage
        }
        batch.use {
            it.draw(textImage, translateX, 0f)
            it.draw(textImage, translateX, 200f)
            it.draw(textImage, translateX, 400f)
            it.draw(textImage, translateX, 600f)
            it.draw(textImage, translateX, 800f)
        }
    }

    enum class RenderType {
        IMAGE,
        TEXT,
        RICH_TEXT,
//        VIDEO,
    }
}
