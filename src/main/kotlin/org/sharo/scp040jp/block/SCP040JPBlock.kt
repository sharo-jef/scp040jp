package org.sharo.scp040jp.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.potion.Effect
import net.minecraft.potion.EffectInstance
import net.minecraft.util.ActionResultType
import net.minecraft.util.Direction
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.BlockRayTraceResult
import net.minecraft.util.math.shapes.ISelectionContext
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.util.math.shapes.VoxelShapeCube
import net.minecraft.util.math.shapes.VoxelShapes
import net.minecraft.world.IBlockReader
import net.minecraft.world.IWorld
import net.minecraft.world.World
import net.minecraft.world.server.ServerWorld
import net.minecraftforge.common.ToolType
import org.sharo.scp040jp.potion.Effects
import java.util.*

class SCP040JPBlock : Block(
    Properties.create(Material.ROCK)
        .hardnessAndResistance(10f, 10f)
        .harvestLevel(0)
        .harvestTool(ToolType.PICKAXE)
        .sound(SoundType.GROUND)
        .tickRandomly()
) {
    override fun onBlockActivated(
        state: BlockState,
        worldIn: World,
        pos: BlockPos,
        player: PlayerEntity,
        handIn: Hand,
        hit: BlockRayTraceResult
    ): ActionResultType {
        val effectInstance = EffectInstance(Effects.SCP040JP_EFFECT, Int.MAX_VALUE, 0, false, false)
        return if (player.addPotionEffect(effectInstance)) ActionResultType.SUCCESS else ActionResultType.FAIL
    }

    override fun onEntityCollision(state: BlockState, worldIn: World, pos: BlockPos, entityIn: Entity) {
        if (entityIn is PlayerEntity) {
            entityIn.addPotionEffect(EffectInstance(Effects.SCP040JP_EFFECT, Int.MAX_VALUE, 0, false, false))
        }
    }

    override fun updatePostPlacement(
        stateIn: BlockState,
        facing: Direction,
        facingState: BlockState,
        worldIn: IWorld,
        currentPos: BlockPos,
        facingPos: BlockPos
    ): BlockState {
        if (currentPos.y == 1) {
            worldIn.destroyBlock(currentPos.down(), true)
        }
        return stateIn
    }

    override fun randomTick(
        p_225542_1_: BlockState,
        p_225542_2_: ServerWorld,
        p_225542_3_: BlockPos,
        p_225542_4_: Random
    ) {
        super.randomTick(p_225542_1_, p_225542_2_, p_225542_3_, p_225542_4_)
        if (p_225542_3_.y == 1) {
            p_225542_2_.destroyBlock(p_225542_3_.down(), true)
        }
    }

//    override fun getShape(
//        p_220053_1_: BlockState,
//        p_220053_2_: IBlockReader,
//        p_220053_3_: BlockPos,
//        p_220053_4_: ISelectionContext
//    ): VoxelShape {
//        return makeCuboidShape(.0, .0, .0, .0, .0, .0)
//    }

    override fun getCollisionShape(
        p_230335_1_: BlockState,
        p_230335_2_: IBlockReader,
        p_230335_3_: BlockPos
    ): VoxelShape {
        return makeCuboidShape(.0, .0, .0, .0, .0, .0)
    }

    override fun getCollisionShape(
        p_220071_1_: BlockState,
        p_220071_2_: IBlockReader,
        p_220071_3_: BlockPos,
        p_220071_4_: ISelectionContext
    ): VoxelShape {
        return makeCuboidShape(.0, .0, .0, .0, .0, .0)
    }
}
