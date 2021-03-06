package net.codedstingray.magicalloy.util.handlers;

import net.codedstingray.magicalloy.MagicAlloy;
import net.codedstingray.magicalloy.init.ModBlocks;
import net.codedstingray.magicalloy.init.ModItems;
import net.codedstingray.magicalloy.util.ICustomModelRegister;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for(Item item: ModItems.ITEMS) {
            if(item instanceof ICustomModelRegister)
                ((ICustomModelRegister) item).registerModels();
            else
                MagicAlloy.proxy.registerItemRenderer(item, 0, "inventory");
        }

        for(Block block: ModBlocks.BLOCKS) {
            if(block instanceof ICustomModelRegister)
                ((ICustomModelRegister) block).registerModels();
            else
                MagicAlloy.proxy.registerItemRenderer(Item.getItemFromBlock(block), 0, "inventory");
        }
    }
}
