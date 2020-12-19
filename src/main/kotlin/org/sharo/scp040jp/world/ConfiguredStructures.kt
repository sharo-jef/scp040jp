package org.sharo.scp040jp.world

import net.minecraft.util.ResourceLocation
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.WorldGenRegistries
import net.minecraft.world.gen.FlatGenerationSettings
import net.minecraft.world.gen.feature.IFeatureConfig
import net.minecraft.world.gen.feature.NoFeatureConfig
import net.minecraft.world.gen.feature.StructureFeature
import net.minecraft.world.gen.feature.structure.Structure
import org.sharo.scp040jp.Core

// ref: https://github.com/TelepathicGrunt/StructureTutorialMod

class ConfiguredStructures {
    companion object {
        @JvmStatic
        val CONFIGURED_WELL: StructureFeature<NoFeatureConfig, out Structure<NoFeatureConfig>> = Structures
            .WELL.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
        @JvmStatic
        fun registerConfiguredStructures() {
            val registry: Registry<StructureFeature<*, *>> = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE
            Registry.register(registry, ResourceLocation(Core.MODID, "configured_well"), CONFIGURED_WELL)
            FlatGenerationSettings.STRUCTURES[Structures.WELL.get()] = CONFIGURED_WELL
        }
    }
}
