package org.sharo.scp040jp.potion

import net.minecraft.potion.Effect
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import org.sharo.scp040jp.Core

class Effects {
    companion object {
        @JvmStatic
        val register: DeferredRegister<Effect> = DeferredRegister.create(ForgeRegistries.POTIONS, Core.MODID)
        @JvmStatic
        val SCP040JP_EFFECT: Effect = SCP040JPEffect()
        @JvmStatic
        val SCP040JP: RegistryObject<Effect> = register.register("scp040jp") { SCP040JP_EFFECT }
    }
}
