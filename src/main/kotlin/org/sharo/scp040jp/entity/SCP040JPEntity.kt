package org.sharo.scp040jp.entity

import net.minecraft.entity.EntityType
import net.minecraft.entity.ILivingEntityData
import net.minecraft.entity.MobEntity
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.attributes.AttributeModifierMap
import net.minecraft.entity.ai.attributes.Attributes
import net.minecraft.entity.ai.goal.LookAtGoal
import net.minecraft.entity.ai.goal.LookRandomlyGoal
import net.minecraft.entity.ai.goal.MeleeAttackGoal
import net.minecraft.entity.ai.goal.RestrictSunGoal
import net.minecraft.entity.passive.OcelotEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.CompoundNBT
import net.minecraft.potion.EffectInstance
import net.minecraft.world.*
import org.sharo.scp040jp.Core
import org.sharo.scp040jp.potion.Effects

class SCP040JPEntity(type: EntityType<out OcelotEntity>, worldIn: World) : OcelotEntity(type, worldIn) {
    companion object {
        @JvmStatic
        val worlds: MutableSet<DimensionType> = mutableSetOf()

        @JvmStatic
        fun setCustomAttributes(): AttributeModifierMap.MutableAttribute {
            return MobEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 1.0)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, .25)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 32.0)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 1.0)
        }
    }

    override fun onInitialSpawn(
        worldIn: IServerWorld,
        difficultyIn: DifficultyInstance,
        reason: SpawnReason,
        spawnDataIn: ILivingEntityData?,
        dataTag: CompoundNBT?
    ): ILivingEntityData? {
        Core.logger.info("spawn")
        worlds.add(worldIn.dimensionType)
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(0, MeleeAttackGoal(this, 1.0, false))
        goalSelector.addGoal(1, LookAtGoal(this, PlayerEntity::class.java, 8f))
        goalSelector.addGoal(2, RestrictSunGoal(this))
        goalSelector.addGoal(3, LookRandomlyGoal(this))
    }

    override fun livingTick() {
        super.livingTick()
        val player = world.getClosestPlayer(this, 32.0)
        if (player != null) {
            attackTarget = player
            lookController.setLookPositionWithEntity(player, 1f, 1f)
            if (player.positionVec.distanceTo(positionVec) < 16) {
                player.addPotionEffect(EffectInstance(Effects.SCP040JP_EFFECT, Int.MAX_VALUE, 0, false, false))
            }
        }
    }

    override fun canSpawn(worldIn: IWorld, spawnReasonIn: SpawnReason): Boolean {
        return super.canSpawn(worldIn, spawnReasonIn)
            && worlds.contains(worldIn.dimensionType)
    }
}
