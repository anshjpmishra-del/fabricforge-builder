package com.example.my_first_mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import java.util.List;

public class CaliditeMod implements ModInitializer {
    public static final String MOD_ID = "my_first_mod";

    public static final Block CALIDITE_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), AbstractBlock.Settings.copy(Blocks.END_STONE).strength(15.0f, 1200.0f).requiresTool());
    public static final Item CALIDITE_INGOT = new Item(new Item.Settings());

    public static final ToolMaterial CALIDITE_MATERIAL = new ToolMaterial() {
        @Override public int getDurability() { return 2500; }
        @Override public float getMiningSpeedMultiplier() { return 10.0f; }
        @Override public float getAttackDamage() { return 5.0f; }
        @Override public int getEnchantability() { return 20; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(CALIDITE_INGOT); }
    };

    public static final Item CALIDITE_SWORD = new SwordItem(CALIDITE_MATERIAL, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(CALIDITE_MATERIAL, 4, -2.4f)));
    public static final Item CALIDITE_PICKAXE = new PickaxeItem(CALIDITE_MATERIAL, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(CALIDITE_MATERIAL, 2, -2.8f)));

    @Override
    public void onInitialize() {
        Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, "calidite_ore"), CALIDITE_ORE);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "calidite_ore"), new BlockItem(CALIDITE_ORE, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "calidite_ingot"), CALIDITE_INGOT);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "calidite_sword"), CALIDITE_SWORD);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "calidite_pickaxe"), CALIDITE_PICKAXE);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> content.add(CALIDITE_INGOT));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.add(CALIDITE_SWORD));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> content.add(CALIDITE_PICKAXE));
    }
}