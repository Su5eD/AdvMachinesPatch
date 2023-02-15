import net.minecraftforge.gradle.common.util.RunConfig
import wtf.gofancy.fancygradle.script.extensions.curse
import wtf.gofancy.fancygradle.script.extensions.curseForge
import wtf.gofancy.fancygradle.script.extensions.deobf
import java.time.LocalDateTime

plugins {
    java
    `maven-publish`
    id("net.minecraftforge.gradle") version "5.1.+"
    id("wtf.gofancy.fancygradle") version "1.1.+"
}

version = "1.1"
group = "mods.su5ed"

val coremodPath: String by project
val versionMc = "1.12.2"

fancyGradle {
    patches {
        resources
        coremods
        asm
        mergetool
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))

    withSourcesJar()
}

minecraft {
    mappings("stable", "39-1.12")

    runs {
        val config = Action<RunConfig> {
            property("forge.logging.console.level", "debug")
            property("fml.coreMods.load", coremodPath)
            workingDirectory = project.file("run").canonicalPath
            source(sourceSets.main.get())
            forceExit = false
        }

        create("client", config)
        create("server", config)
    }
}

repositories {
    maven {
        name = "IC2"
        url = uri("https://maven.ic2.player.to/")
    }
    curseForge()
}

dependencies {
    minecraft(group = "net.minecraftforge", name = "forge", version = "1.12.2-14.23.5.2860")

    implementation(fg.deobf(curse("advanced-machines", 251205, 2667002)))
    implementation(fg.deobf(group = "net.industrial-craft", name = "industrialcraft-2", version = "2.8.220-ex112"))
}

tasks {
    jar {
        manifest.attributes(
            "Specification-Title" to project.name,
            "Specification-Vendor" to "Su5eD",
            "Specification-Version" to "1",
            "Implementation-Title" to project.name,
            "Implementation-Vendor" to "Su5eD",
            "Implementation-Version" to project.version,
            "Implementation-Timestamp" to LocalDateTime.now(),
            "FMLCorePlugin" to coremodPath,
            "FMLCorePluginContainsFMLMod" to true
        )
    }

    processResources {
        inputs.properties(
            "version" to project.version,
            "mcversion" to versionMc
        )

        filesMatching("mcmod.info") {
            expand(
                "version" to project.version,
                "mcversion" to versionMc
            )
        }
    }
}