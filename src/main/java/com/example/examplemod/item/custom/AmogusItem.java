package com.example.examplemod.item.custom;

import com.example.examplemod.item.ModItems;
import com.example.examplemod.sound.ModSounds;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.sound.SoundEvent;

public class AmogusItem extends Item {

    //private static final SoundEvent eatingSound;
    //private static final SoundEvent finishSound;
    //private static final Item replacementItem;


    public AmogusItem(Properties pProperties) {
        super(pProperties
                .food(new FoodProperties.Builder()
                        .meat()
                        .nutrition(5)
                        .saturationMod(0.5F)
                        .alwaysEat()
                        .build())
                .rarity(Rarity.EPIC)
                .stacksTo(1));


    }

    @Override
    public int getUseDuration(ItemStack stack) { return 44; }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity)
    {
        if (!world.isClientSide && entity instanceof ServerPlayer serverPlayer) {
            world.playSound(null, entity.blockPosition(),
                    ModSounds.AMOGUS_KILLED.get(), SoundSource.PLAYERS,
                    1.0f, 1.0f
            );
        }
        super.finishUsingItem(stack, world, entity);

        return new ItemStack(ModItems.KILLED_AMOGUS.get());
    }
}

