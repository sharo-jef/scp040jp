package org.sharo.scp040jp.item

import net.minecraft.item.*
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import org.sharo.scp040jp.Core
import org.sharo.scp040jp.block.Blocks
import org.sharo.scp040jp.entity.EntityTypes

class Items {
    companion object {
        @JvmStatic
        val register: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS, Core.MODID)
        @JvmStatic
        val SCP040JP: RegistryObject<BlockItem> = register.register("scp040jp") {
            BlockItem(
                Blocks.SCP040JP_BLOCK,
                Item.Properties()
                    .group(ItemGroup.MATERIALS)
                    .maxStackSize(64)
                    .rarity(Rarity.COMMON)
            )
        }
        @JvmStatic
        val SCP040JP_EGG: RegistryObject<SpawnEggItem> = register.register("scp040jp_spawn_egg") {
            SCP040JPEgg(EntityTypes.SCP040JP, 0xffffff, 0x000000, Item.Properties().group(ItemGroup.MISC))
}
    }
}
