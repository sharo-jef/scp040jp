package org.sharo.scp040jp.item

import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.Rarity
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import org.sharo.scp040jp.Core
import org.sharo.scp040jp.block.Blocks

class Items {
    companion object {
        @JvmStatic
        val register: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS, Core.MODID)
        @JvmStatic
        val SCP040JP: RegistryObject<Item> = register.register("scp040jp") {
            BlockItem(
                Blocks.SCP040JP_BLOCK,
                Item.Properties()
                    .group(ItemGroup.MATERIALS)
                    .maxStackSize(64)
                    .rarity(Rarity.COMMON)
            )
        }
    }
}
