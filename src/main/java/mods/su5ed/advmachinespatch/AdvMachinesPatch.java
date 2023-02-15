package mods.su5ed.advmachinespatch;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = AdvMachinesPatch.MODID, name = AdvMachinesPatch.NAME)
public class AdvMachinesPatch {
    public static final String MODID = "advmachinespatch";
    public static final String NAME = "Advanced Machines Patcher";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {}

    @EventHandler
    public void init(FMLInitializationEvent event) {}
}
