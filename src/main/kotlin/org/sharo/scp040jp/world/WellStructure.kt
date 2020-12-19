package org.sharo.scp040jp.world

import com.mojang.serialization.Codec
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MutableBoundingBox
import net.minecraft.util.registry.DynamicRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.MobSpawnInfo
import net.minecraft.world.gen.ChunkGenerator
import net.minecraft.world.gen.GenerationStage
import net.minecraft.world.gen.feature.NoFeatureConfig
import net.minecraft.world.gen.feature.jigsaw.JigsawManager
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece
import net.minecraft.world.gen.feature.structure.Structure
import net.minecraft.world.gen.feature.structure.Structure.IStartFactory
import net.minecraft.world.gen.feature.structure.StructureStart
import net.minecraft.world.gen.feature.structure.VillageConfig
import net.minecraft.world.gen.feature.template.TemplateManager
import org.sharo.scp040jp.Core

// ref: https://github.com/TelepathicGrunt/StructureTutorialMod

class WellStructure(codec: Codec<NoFeatureConfig>) : Structure<NoFeatureConfig>(codec) {
    override fun getStartFactory(): IStartFactory<NoFeatureConfig> = IStartFactory {
        structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn ->
            Start(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn)
    }

    override fun getDecorationStage(): GenerationStage.Decoration = GenerationStage.Decoration.SURFACE_STRUCTURES

    override fun getDefaultSpawnList(): MutableList<MobSpawnInfo.Spawners> = mutableListOf()

    override fun getDefaultCreatureSpawnList(): MutableList<MobSpawnInfo.Spawners> = mutableListOf()

    class Start(
        structureIn: Structure<NoFeatureConfig>,
        chunkX: Int,
        chunkZ: Int,
        mutableBoundingBox: MutableBoundingBox,
        referenceIn: Int,
        seedIn: Long
    ) : StructureStart<NoFeatureConfig>(
        structureIn,
        chunkX,
        chunkZ,
        mutableBoundingBox,
        referenceIn,
        seedIn
    ) {
        override fun func_230364_a_(
            dynamicRegistrymanager: DynamicRegistries,
            chunkGenerator: ChunkGenerator,
            templateManagerIn: TemplateManager,
            chunkX: Int,
            chunkZ: Int,
            biomeIn: Biome,
            config: NoFeatureConfig
        ) {
            val x = (chunkX shl 4) + 7
            val z = (chunkZ shl 4) + 7
            val blockPos = BlockPos(x, 0, z)

            JigsawManager.func_242837_a(
                dynamicRegistrymanager,
                VillageConfig (
                    {
                        dynamicRegistrymanager.getRegistry(Registry.JIGSAW_POOL_KEY)
                            .getOrDefault(ResourceLocation(Core.MODID, "well/start_pool"))
                    },
                    50
                ),
                ::AbstractVillagePiece,
                chunkGenerator,
                templateManagerIn,
                blockPos,
                components,
                rand,
                true,
                true
            )

            components.forEach { it.offset(0, -251, 0) }
            components.forEach { it.boundingBox.minY -= 1 }

            recalculateStructureSize()

            Core.logger.debug("Well at (${components[0].boundingBox.minX}, ${components[0].boundingBox.minY}, ${components[0].boundingBox.minZ})")
        }
    }
}
