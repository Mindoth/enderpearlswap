package net.mindoth.enderpearlswap.event;

import net.mindoth.enderpearlswap.Enderpearlswap;
import net.mindoth.enderpearlswap.config.EnderpearlswapConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Enderpearlswap.MOD_ID)
public class CommonEvents {

    @SubscribeEvent
    public static void enderpearlEvent(final ProjectileImpactEvent.Throwable event) {
        Entity entity = event.getEntity();

        //Check if throwable is an enderpearl
        if ( entity instanceof EnderPearlEntity) {
            World level = entity.level;

            //Only on serverside
            if ( !level.isClientSide ) {
                ThrowableEntity throwable = event.getThrowable();
                Entity owner = throwable.getOwner();

                //Distance the entity needs to be from throwable to be teleported
                Double distance = EnderpearlswapConfig.LANDING_AREA_DISTANCE.get();

                //Make a list of all entities a block away from the throwable
                List<Entity> entitiesAround = level.getEntities(entity, entity.getBoundingBox().inflate(distance, distance, distance));

                for ( Entity listedEntity : entitiesAround ) {

                    //Check if both entities are living entities
                    if ( owner instanceof LivingEntity && listedEntity instanceof LivingEntity ) {

                        //Check if both entities are still in the same dimension
                        if ( owner.level == listedEntity.level ) {
                            listedEntity.moveTo(owner.getX(), owner.getY(), owner.getZ());
                        }
                    }
                }
            }
        }
    }
}
