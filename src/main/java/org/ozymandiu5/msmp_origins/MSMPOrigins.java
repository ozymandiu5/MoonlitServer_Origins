package org.ozymandiu5.msmp_origins;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// The value here should match an entry in the META-INF/mods.toml file
@SuppressWarnings("removal")
@Mod(MSMPOrigins.MODID)
public class MSMPOrigins {

    public static final String MODID = "msmp_origins";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<Block> MOONSILVER_PILE_BLOCK = BLOCKS.register("moonsilver_pile", () -> new CoinPileBlock(BlockBehaviour.Properties.of()
    		.mapColor(MapColor.COLOR_CYAN).forceSolidOff().strength(0.1F)
    		.sound(SoundType.CHAIN).pushReaction(PushReaction.DESTROY)
    		.isViewBlocking((p_187417_, p_187418_, p_187419_) -> p_187417_.getValue(CoinPileBlock.LAYERS) >= 8)));

    public static final RegistryObject<Block> MOONSILVER_BLOCK_BLOCK = BLOCKS.register("moonsilver_block", () -> new Block(BlockBehaviour.Properties.of()
    		.mapColor(MapColor.COLOR_CYAN).forceSolidOff().strength(0.1F)
    		.sound(SoundType.CHAIN).pushReaction(PushReaction.DESTROY)
    		));

    
    public static final RegistryObject<Item> MOONSILVER_BLOCK = ITEMS.register("moonsilver_block", () -> new MoonsilverBlockItem(MOONSILVER_BLOCK_BLOCK.get(), new Item.Properties(), 256));    
    public static final RegistryObject<Item> MOONSILVER_PILE = ITEMS.register("moonsilver_pile", () -> new MoonsilverBlockItem(MOONSILVER_PILE_BLOCK.get(), new Item.Properties(), 32));    
    public static final RegistryObject<Item> MOONSILVER_STACK = ITEMS.register("moonsilver_stack", () -> new MoonsilverItem(new Item.Properties(), 8));
    public static final RegistryObject<Item> MOONSILVER = ITEMS.register("moonsilver", () -> new CoinItem(new Item.Properties(), 1));
    public static final RegistryObject<Item> ICON = ITEMS.register("icon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<CreativeModeTab> MSMP_TAB = CREATIVE_MODE_TABS.register("msmp", () -> 
    	CreativeModeTab.builder().withTabsBefore(CreativeModeTabs.COMBAT).icon(() -> ICON.get().getDefaultInstance()).displayItems((parameters, output) -> {
        output.accept(ICON.get()); 
    }).title(Component.translatable("tab.msmp_origins.msmp")).build());

    public MSMPOrigins() {
    	LOGGER.info("Moonlit lamps is goated with the sauce.");
    	
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == MSMP_TAB.get()) {
        	event.accept(MOONSILVER);
        	event.accept(MOONSILVER_STACK);
        	event.accept(MOONSILVER_PILE);
        	event.accept(MOONSILVER_BLOCK);
        }
    }	


    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

    }
}
