package org.sharo.scp040jp.block

import net.minecraft.block.Block
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import org.sharo.scp040jp.Core

class Blocks {
    companion object {
        @JvmStatic
        val register: DeferredRegister<Block> = DeferredRegister.create(ForgeRegistries.BLOCKS, Core.MODID)
        @JvmStatic
        val SCP040JP_BLOCK: Block = SCP040JPBlock()
        @JvmStatic
        val SCP040JP: RegistryObject<Block> = register.register("scp040jp") { SCP040JP_BLOCK }
    }
}
