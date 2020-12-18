package org.sharo.scp040jp.entity

import net.minecraft.entity.EntityClassification
import net.minecraft.entity.EntityType
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import org.sharo.scp040jp.Core

class EntityTypes {
    companion object {
        @JvmStatic
        val register: DeferredRegister<EntityType<*>> = DeferredRegister.create(ForgeRegistries.ENTITIES, Core.MODID)
        @JvmStatic
        val SCP040JP: RegistryObject<EntityType<SCP040JPEntity>> = register.register("scp040jp") {
            EntityType.Builder.create(::SCP040JPEntity, EntityClassification.CREATURE)
                .size(1f, 1f)
                .build(ResourceLocation(Core.MODID, "scp040jp").toString())
        }
    }
}
