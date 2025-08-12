package com.example.examplemod;

import com.example.examplemod.sound.ModSounds;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTabs;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.block.entity.ModBlockEntities;
import com.example.examplemod.item.ModItems;
import com.example.examplemod.item.ModCreativeModTabs;
import com.example.examplemod.screen.BrickFurnaceScreen;
import com.example.examplemod.screen.ModMenuTypes;


@Mod(CrazyFurnacesMod.MOD_ID)
public class CrazyFurnacesMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "testmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CrazyFurnacesMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModSounds.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {}

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.BRICKFURNACE_MENU.get(), BrickFurnaceScreen::new);
        }
    }
}
