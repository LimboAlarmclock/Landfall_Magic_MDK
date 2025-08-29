package net.Limbo.landfallmagic;

import java.util.List;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.core.registries.BuiltInRegistries;


public class Config {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Log when dirt block is detected")
            .define("logDirtBlock", true);

    public static final ModConfigSpec.IntValue MAGIC_NUMBER_INTRODUCTION = BUILDER
            .comment("Magic number for introduction")
            .defineInRange("magicNumberIntroduction", 42, 0, Integer.MAX_VALUE);

    // (Optional second number, if you really want two)
    public static final ModConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("Another magic number")
            .defineInRange("magicNumber", 7, 0, Integer.MAX_VALUE);

    public static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("List of item strings")
            .defineList("itemStrings", List.of("example_item"), o -> o instanceof String);

    public static final ModConfigSpec SPEC = BUILDER.build();
}