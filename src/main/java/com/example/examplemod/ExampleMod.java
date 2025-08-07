package com.example.examplemod;

import com.example.examplemod.block.CustomBlock;
import com.example.examplemod.block.BrickFurnace;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod.MODID)
public class ExampleMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "testmod";

    private static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);


    // регистрация новых блоков + предметов + прописка в креатив меню
    public static final RegistryObject<Block> CUSTOM_BLOCK =
            BLOCKS.register("custom_block", CustomBlock::new);

    public static final RegistryObject<Block> BRICKFURNACE_BLOCK =
            BLOCKS.register("brickfurnace_block", BrickFurnace::new);

    public static final RegistryObject<Item> CUSTOM_BLOCK_ITEM =
            ITEMS.register("custom_block", () ->
                    new BlockItem(CUSTOM_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> BRICKFURNACE_BLOCK_ITEM =
            ITEMS.register("brickfurnace_block", () ->
                    new BlockItem(BRICKFURNACE_BLOCK.get(), new Item.Properties()));

    // Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.translatable("item_group." + MODID + ".example"))
            .icon(() -> CUSTOM_BLOCK_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(CUSTOM_BLOCK_ITEM.get()); // Тут нужно прописывать предметы (items of blocks, not blocks)
                output.accept(BRICKFURNACE_BLOCK_ITEM.get());
            }).build());


    public ExampleMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        // Подписка на добавление в креатив
        //modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(CUSTOM_BLOCK_ITEM);
    }
}
