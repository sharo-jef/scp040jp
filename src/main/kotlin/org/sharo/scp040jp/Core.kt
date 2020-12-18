package org.sharo.scp040jp

import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes
import net.minecraft.world.World
import net.minecraft.world.gen.FlatChunkGenerator
import net.minecraft.world.gen.feature.structure.Structure
import net.minecraft.world.gen.settings.DimensionStructuresSettings
import net.minecraft.world.gen.settings.StructureSeparationSettings
import net.minecraft.world.server.ServerWorld
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.world.BiomeLoadingEvent
import net.minecraftforge.event.world.WorldEvent
import net.minecraftforge.eventbus.api.EventPriority
import net.minecraftforge.fml.DeferredWorkQueue
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.minecraftforge.registries.ForgeRegistries
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.sharo.scp040jp.block.Blocks
import org.sharo.scp040jp.entity.EntityTypes
import org.sharo.scp040jp.entity.SCP040JPEntity
import org.sharo.scp040jp.item.Items
import org.sharo.scp040jp.network.PacketHandler
import org.sharo.scp040jp.potion.Effects

@Mod(Core.MODID)
@Mod.EventBusSubscriber(modid = Core.MODID)
class Core {
    companion object {
        const val MODID = "scp040jp"
        @JvmStatic
        val logger: Logger = LogManager.getLogger()
    }

    init {
        val bus = FMLJavaModLoadingContext.get().modEventBus

        Items.register.register(bus)
        Blocks.register.register(bus)
        EntityTypes.register.register(bus)
        Effects.register.register(bus)

        val forgeBus = MinecraftForge.EVENT_BUS

        forgeBus.addListener<WorldEvent.Load>(EventPriority.NORMAL) { addDimensionalSpacing(it) }
        forgeBus.addListener<BiomeLoadingEvent>(EventPriority.HIGH) { biomeModification(it) }

        bus.addListener<FMLCommonSetupEvent> { setup(it) }
        bus.addListener<FMLClientSetupEvent> { setupClient(it) }
        bus.addListener<InterModEnqueueEvent> { enqueueIMC(it) }
        bus.addListener<InterModProcessEvent> { processIMC(it) }

        PacketHandler.register()
    }

    private fun setup(event: FMLCommonSetupEvent) {
        DeferredWorkQueue.runLater() {
            GlobalEntityTypeAttributes.put(EntityTypes.SCP040JP.get(), SCP040JPEntity.setCustomAttributes().create())
        }
    }

    private fun setupClient(event: FMLClientSetupEvent) {

    }

    private fun enqueueIMC(event: InterModEnqueueEvent) {

    }

    private fun processIMC(event: InterModProcessEvent) {

    }

    private fun addDimensionalSpacing(event: WorldEvent.Load) {
        if (event.world is ServerWorld) {
            val world = event.world as ServerWorld
            if (
                world.chunkProvider.chunkGenerator is FlatChunkGenerator
                && world.dimensionKey == World.OVERWORLD
            ) {
                return
            }

            val tempMap = HashMap(world.chunkProvider.generator.func_235957_b_().func_236195_a_())
            tempMap.put(TODO(), DimensionStructuresSettings.field_236191_b_.get(TODO()))
            world.chunkProvider.generator.func_235957_b_().field_236193_d_ = tempMap
        }
    }

    private fun biomeModification(event: BiomeLoadingEvent) {
        event.generation.structures.add {
            TODO()
        }
    }
}
