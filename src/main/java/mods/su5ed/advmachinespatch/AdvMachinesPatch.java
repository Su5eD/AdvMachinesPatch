package mods.su5ed.advmachinespatch;

import com.chocohead.AdvMachines.AdvancedMachines;
import com.chocohead.AdvMachines.te.AdvancedMachineTEs;
import ic2.api.item.IC2Items;
import ic2.core.IC2;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = AdvMachinesPatch.MODID, name = AdvMachinesPatch.NAME, dependencies = "after:advanced_machines@[61.0.0, )")
public class AdvMachinesPatch {
    public static final String MODID = "advmachinespatch";
    public static final String NAME = "Advanced Machines Patcher";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {}

    @EventHandler
    public void init(FMLInitializationEvent event) {}

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (IC2.version.isExperimental()) {
            GameRegistry.addShapedRecipe(
                new ResourceLocation(MODID, "thermal_washer_oredict"),
                null,
                AdvancedMachines.machines.getItemStack(AdvancedMachineTEs.thermal_washer),
                "###",
                "#M#",
                "#X#",
                '#',
                "dustSulfur",
                'M',
                IC2Items.getItem("te", "ore_washing_plant"),
                'X',
                IC2Items.getItem("resource", "advanced_machine")
            );
        }
    }
}
