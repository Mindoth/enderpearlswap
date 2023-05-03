package net.mindoth.enderpearlswap.event;

import net.mindoth.enderpearlswap.EnderPearlSwap;
import net.mindoth.enderpearlswap.config.EnderPearlSwapConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = EnderPearlSwap.MOD_ID)
public class CommonEvents {

    @SubscribeEvent
    public static void enderpearlEventArea(final ProjectileImpactEvent.Throwable event) {
        if ( !EnderPearlSwapConfig.MODE.get() ) {

            Entity entity = event.getEntity();

            //Check if throwable is an enderpearl
            if (entity instanceof EnderPearlEntity) {
                World level = entity.level;

                //Only on serverside
                if (!level.isClientSide) {
                    ThrowableEntity throwable = event.getThrowable();
                    Entity owner = throwable.getOwner();

                    //Distance the entity needs to be from throwable to be teleported
                    Double distance = EnderPearlSwapConfig.LANDING_AREA_DISTANCE.get();

                    //Make a list of all entities a block away from the throwable
                    List<Entity> entitiesAround = level.getEntities(entity, entity.getBoundingBox().inflate(distance, distance, distance));

                    for (Entity listedEntity : entitiesAround) {

                        //Check if both entities are living entities
                        if (owner instanceof LivingEntity && listedEntity instanceof LivingEntity) {

                            //Check if both entities are still in the same dimension
                            if (owner.level == listedEntity.level) {
                                listedEntity.moveTo(owner.getX(), owner.getY(), owner.getZ());
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void enderpearlEventDirect(final LivingHurtEvent event) {
        if ( EnderPearlSwapConfig.MODE.get() ) {
            if (event.getSource().getDirectEntity() instanceof EnderPearlEntity) {
                Entity owner = event.getSource().getEntity();
                LivingEntity target = event.getEntityLiving();
                Vector3d sourcePos = owner.position();

                //Check if both entities are still in the same dimension
                if (owner.level == target.level) {
                    target.moveTo(sourcePos);
                }
            }
        }
    }
}
