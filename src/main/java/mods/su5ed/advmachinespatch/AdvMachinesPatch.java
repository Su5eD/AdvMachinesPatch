package mods.su5ed.advmachinespatch;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = AdvMachinesPatch.MODID, name = AdvMachinesPatch.NAME, version = AdvMachinesPatch.VERSION)
public class AdvMachinesPatch
{
    public static final String MODID = "advmachinespatch";
    public static final String NAME = "Advanced Machines Patcher";
    public static final String VERSION = "1.0";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {}

    @EventHandler
    public void init(FMLInitializationEvent event) {}
}
