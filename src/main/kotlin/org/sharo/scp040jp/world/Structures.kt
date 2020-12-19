package org.sharo.scp040jp.world

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import net.minecraft.world.gen.feature.NoFeatureConfig
import net.minecraft.world.gen.feature.structure.Structure
import net.minecraft.world.gen.settings.DimensionStructuresSettings
import net.minecraft.world.gen.settings.StructureSeparationSettings
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import org.sharo.scp040jp.Core

// ref: https://github.com/TelepathicGrunt/StructureTutorialMod

class Structures {
    companion object {
        @JvmStatic
        val register: DeferredRegister<Structure<*>> = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Core.MODID)
        @JvmStatic
        val WELL: RegistryObject<WellStructure> = register.register("well") {
            WellStructure(NoFeatureConfig.field_236558_a_)
        }

        @JvmStatic
        fun setupStructures() {
            setupMapSpacingAndLand(
                WELL.get(),
                StructureSeparationSettings(
                    100,  // 10
                    50,  // 5
                    1234567890
                ),
                true
            )
        }

        @JvmStatic
        fun <F : Structure<*>> setupMapSpacingAndLand(
            structure: F,
            structureSeparationSettings: StructureSeparationSettings,
            transformSurroundingLand: Boolean
        ) {
            Structure.NAME_STRUCTURE_BIMAP[structure.registryName.toString()] = structure
            if (transformSurroundingLand) {
                Structure.field_236384_t_ = ImmutableList.builder<Structure<*>>()
                    .addAll(Structure.field_236384_t_)
                    .add(structure)
                    .build()
            }
            DimensionStructuresSettings.field_236191_b_ = ImmutableMap.builder<Structure<*>, StructureSeparationSettings>()
                .putAll(DimensionStructuresSettings.field_236191_b_)
                .put(structure, structureSeparationSettings)
                .build()
        }
    }
}
