package mods.su5ed.advmachinespatch.asm;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

import java.util.Collections;

public class ADummyContainer extends DummyModContainer {

    public ADummyContainer()
    {
        super(new ModMetadata());
        ModMetadata meta = super.getMetadata();
        meta.modId = "advmachinespatchcore";
        meta.name = "AdvMachinesPatchCoreMod";
        meta.version = "1.0";
        meta.authorList = Collections.singletonList("Su5eD");
        meta.description = "A coremod that fixes Advanced Machines crashing with newer ic2 builds";
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller)
    {
        bus.register(this);
        return true;
    }
}
