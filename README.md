# PlayerDemo

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

This project was generated with a Kotlin project template that includes Kotlin application launchers and [KTX](https://libktx.github.io/) utilities.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `android`: Android mobile platform. Needs Android SDK.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `android:lint`: performs Android project validation.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.

# LibGDX 相关依赖包解释

这些是 LibGDX 游戏开发框架的相关依赖包，下面我来解释它们的主要功能：

## 核心 LibGDX 包

- **com.badlogicgames.gdx:gdx**: LibGDX 的核心库，提供基础游戏开发功能
- **com.badlogicgames.gdx:gdx-box2d**: 2D 物理引擎，用于实现物理效果如碰撞、重力等
- **com.badlogicgames.gdx:gdx-freetype**: 字体渲染库，支持动态生成不同大小的字体
- **com.badlogicgames.gdx:gdx-ai**: 人工智能库，提供寻路、行为树等 AI 功能
- **com.badlogicgames.gdx-video**: 视频播放支持，允许在游戏中播放视频

## 扩展库

- **com.badlogicgames.ashley:ashley**: 实体组件系统 (ECS)，用于管理游戏对象和逻辑
- **com.badlogicgames.box2dlights:box2dlights**: Box2D 的光照扩展，为 2D 游戏添加动态光影效果
- **com.github.tommyettinger:anim8-gdx**: 动画库，简化动画创建和管理
- **com.kotcrab.vis:vis-ui**: 高级 UI 工具包，提供更丰富的 UI 组件和样式

## KTX 库 (Kotlin LibGDX 扩展)

KTX 是一系列为 Kotlin 开发者提供的 LibGDX 扩展库，使 LibGDX 在 Kotlin 中使用更加便捷：

- **ktx-app**: 简化应用程序生命周期管理
- **ktx-actors**: Scene2D actors 的 Kotlin 扩展
- **ktx-ashley**: Ashley ECS 的 Kotlin 扩展
- **ktx-assets/ktx-assets-async**: 资源加载和管理的扩展，支持异步加载
- **ktx-async**: 协程支持，简化异步操作
- **ktx-box2d**: Box2D 物理引擎的 Kotlin 扩展
- **ktx-collections**: 集合类的扩展
- **ktx-freetype/ktx-freetype-async**: FreeType 字体的扩展，支持异步加载
- **ktx-graphics**: 图形相关的扩展
- **ktx-i18n**: 国际化支持
- **ktx-inject**: 依赖注入支持
- **ktx-json**: JSON 解析和生成的扩展
- **ktx-log**: 日志功能扩展
- **ktx-math**: 数学相关的扩展，如向量、矩阵运算
- **ktx-preferences**: 游戏设置和偏好存储
- **ktx-reflect**: 反射相关的扩展
- **ktx-scene2d**: Scene2D UI 系统的扩展
- **ktx-style**: UI 样式管理的扩展
- **ktx-tiled**: Tiled 地图编辑器支持
- **ktx-vis/ktx-vis-style**: VisUI 库的 Kotlin 扩展

## 特殊功能库

- **ktx-artemis**: Artemis ECS 框架的 Kotlin 扩展
- **ktx-ai**: AI 相关功能的 Kotlin 扩展
