package com.example.examplemod.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.example.examplemod.CrazyFurnacesMod;
import com.example.examplemod.block.ModBlocks;

public class ModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CrazyFurnacesMod.MODID);

    public static final RegistryObject<BlockEntityType<BrickFurnaceBlockEntity>> BrickFurnace_BE =
            BLOCK_ENTITIES.register("gem_polishing_be", () ->
                    BlockEntityType.Builder.of(BrickFurnaceBlockEntity::new,
                            ModBlocks.BRICK_FURNACE.get()).build(null)); //XXX


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}