package com.example.examplemod.sound;

import com.example.examplemod.CrazyFurnacesMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CrazyFurnacesMod.MOD_ID);

    public static final RegistryObject<SoundEvent> AMOGUS_KILLED =
            SOUND_EVENTS.register("amogus_killed_sound", () ->
                    SoundEvent.createVariableRangeEvent(new ResourceLocation(CrazyFurnacesMod.MOD_ID, "item.amogus_killed")));

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

}
