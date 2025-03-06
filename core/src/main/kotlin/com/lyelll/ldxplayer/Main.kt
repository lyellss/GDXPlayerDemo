package com.lyelll.ldxplayer

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter.Linear
import com.badlogic.gdx.graphics.g2d.SpriteBatch
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

    private var image1 = Texture("44.png".toInternalFile(), true).apply {
        setFilter(Linear, Linear)
    }

    private var image2 = Texture("44.png".toInternalFile(), true).apply {
        setFilter(Linear, Linear)
    }

    private var tempImage: Texture = image1

    private val batch = SpriteBatch()

    // 添加变量来跟踪图片位置
    private var imageX = -image1.width.toFloat() // 从屏幕左侧外开始
    private val speed = 800f // 每秒移动的像素数

    override fun render(delta: Float) {
        // 如果图片移出屏幕右侧，重置到左侧
        if (imageX > Gdx.graphics.width) { // 假设屏幕宽度为800像素
            imageX -= Gdx.graphics.width
            tempImage = image1
            image1 = image2
            image2 = tempImage
        }
        clearScreen(red = 0.7f, green = 0.7f, blue = 0.7f)
        batch.use {
            it.draw(image1, imageX, 0f)
            it.draw(image2, imageX - image1.width, 0f)
        }
        // 更新图片位置
        imageX += speed * delta
    }

    override fun dispose() {
        image1.disposeSafely()
        batch.disposeSafely()
    }
}
