package net.Limbo.landfallmagic;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(landfallmagic.MODID)
public class landfallmagic {
    public static final String MODID = "landfallmagic";
    public static final Logger LOGGER = LogUtils.getLogger();

    // Example block/item (you can remove these later if you want)
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Custom Creative Tab for your fire node blocks
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> LANDFALL_MAGIC_TAB =
            CREATIVE_MODE_TABS.register("landfall_magic_tab", () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.landfallmagic"))
                            .withTabsBefore(CreativeModeTabs.COMBAT)
                            .icon(() -> ModBlocks.FIRE_NODE_ITEM.get().getDefaultInstance())
                            .icon(() -> ModBlocks.WATER_NODE_ITEM.get().getDefaultInstance())
                            .icon(() -> ModBlocks.EARTH_NODE_ITEM.get().getDefaultInstance())
                            .icon(() -> ModBlocks.AIR_NODE_ITEM.get().getDefaultInstance())
                            .icon(() -> ModBlocks.DARK_NODE_ITEM.get().getDefaultInstance())
                            .icon(() -> ModBlocks.LIGHT_NODE_ITEM.get().getDefaultInstance())
                            .icon(() -> ModBlocks.ORDER_NODE_ITEM.get().getDefaultInstance())
                            .icon(() -> ModBlocks.CHAOS_NODE_ITEM.get().getDefaultInstance())
                            .icon(() -> ModBlocks.CREATION_NODE_ITEM.get().getDefaultInstance())
                            .icon(() -> ModBlocks.DESTRUCTION_NODE_ITEM.get().getDefaultInstance())
                            .displayItems((parameters, output) -> {
                                // Add your fire node blocks to the custom tab
                                output.accept(ModBlocks.FIRE_NODE_ITEM.get());
                                output.accept(ModBlocks.WATER_NODE_ITEM.get());
                                output.accept(ModBlocks.EARTH_NODE_ITEM.get());
                                output.accept(ModBlocks.AIR_NODE_ITEM.get());
                                output.accept(ModBlocks.DARK_NODE_ITEM.get());
                                output.accept(ModBlocks.LIGHT_NODE_ITEM.get());
                                output.accept(ModBlocks.ORDER_NODE_ITEM.get());
                                output.accept(ModBlocks.CHAOS_NODE_ITEM.get());
                                output.accept(ModBlocks.CREATION_NODE_ITEM.get());
                                output.accept(ModBlocks.DESTRUCTION_NODE_ITEM.get());
                            }).build());

    public landfallmagic(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        // Register your fire node blocks
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.ITEMS.register(modEventBus);

        // Register creative tabs
        CREATIVE_MODE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        // Config registration
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM LANDFALL MAGIC COMMON SETUP");

        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("Magic Number: {}{}",
                Config.MAGIC_NUMBER_INTRODUCTION.get(),
                Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) ->
                LOGGER.info("ITEM >> {}", item));
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from Landfall Magic server starting");
        LOGGER.info("Fire nodes are ready for magical adventures!");
    }
}