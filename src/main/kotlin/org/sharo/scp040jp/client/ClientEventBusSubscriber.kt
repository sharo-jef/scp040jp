package org.sharo.scp040jp.client

import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.client.registry.RenderingRegistry
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import org.sharo.scp040jp.Core
import org.sharo.scp040jp.client.render.SCP040JPRenderer
import org.sharo.scp040jp.entity.EntityTypes

@EventBusSubscriber(modid = Core.MODID, bus = EventBusSubscriber.Bus.MOD, value = [Dist.CLIENT])
class ClientEventBusSubscriber {
    companion object {
        @JvmStatic
        @SubscribeEvent
        fun onClientSetup(event: FMLClientSetupEvent) {
            RenderingRegistry.registerEntityRenderingHandler(EntityTypes.SCP040JP.get(), ::SCP040JPRenderer)
        }
    }
}
