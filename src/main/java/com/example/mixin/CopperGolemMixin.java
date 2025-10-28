package com.example.mixin;

import net.minecraft.entity.EntityType;
//import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.CopperGolemEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CopperGolemEntity.class)
public abstract class CopperGolemMixin extends GolemEntity {
    
    // Required constructor - don't modify this
    protected CopperGolemMixin(EntityType<? extends GolemEntity> entityType, World world) {
        super(entityType, world);
    }
    
    @Inject(method = "createCopperGolemAttributes", at = @At("RETURN"), cancellable = true)
    private static void modifyCopperGolemSpeed(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        // Get the original attribute builder
        DefaultAttributeContainer.Builder original = cir.getReturnValue();
        
        // Add modified movement speed (vanilla is 0.25f, let's make it 0.4f - 60% faster)
        DefaultAttributeContainer.Builder modified = original.add(EntityAttributes.MOVEMENT_SPEED, 0.4f);
		modified.add(EntityAttributes.MAX_HEALTH, 30.0);
        
        // You can also modify other attributes if you want:
        // modified.add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0); // More health
        // modified.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0); // More attack damage
        
        // Return the modified attributes
        cir.setReturnValue(modified);
    }
}