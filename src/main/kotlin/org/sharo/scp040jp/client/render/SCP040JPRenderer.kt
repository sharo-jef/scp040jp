package org.sharo.scp040jp.client.render

import net.minecraft.client.renderer.entity.EntityRendererManager
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.client.renderer.entity.model.OcelotModel
import net.minecraft.util.ResourceLocation
import org.sharo.scp040jp.Core
import org.sharo.scp040jp.entity.SCP040JPEntity

class SCP040JPRenderer(
    rendererManager: EntityRendererManager
) : MobRenderer<SCP040JPEntity, OcelotModel<SCP040JPEntity>>(
        rendererManager, OcelotModel<SCP040JPEntity>(.1f), .5f
) {
    private val texture: ResourceLocation = ResourceLocation(Core.MODID, "textures/entity/neko.png")

    override fun getEntityTexture(entity: SCP040JPEntity): ResourceLocation {
        return texture
    }
}
