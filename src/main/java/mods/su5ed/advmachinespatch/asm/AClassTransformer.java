package mods.su5ed.advmachinespatch.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AClassTransformer implements IClassTransformer {
    private static final Logger LOGGER = LogManager.getLogger("AdvMachinesPatchCoreMod");
    private static final Map<String, String> REPLACEMENTS = new HashMap<>();

    static {
        REPLACEMENTS.put("ic2/core/block/invslot/InvSlotProcessableGeneric", "(Lic2/core/block/TileEntityInventory;Ljava/lang/String;ILic2/api/recipe/IMachineRecipeManager;)V");
        REPLACEMENTS.put("ic2/core/block/invslot/InvSlotOutput", "(Lic2/core/block/TileEntityInventory;Ljava/lang/String;I)V");
        REPLACEMENTS.put("ic2/core/block/invslot/InvSlotUpgrade", "(Lic2/core/block/TileEntityInventory;Ljava/lang/String;I)V");
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (name.contains("com.chocohead.AdvMachines.te.TileEntityHeatingMachine"))
            return patchTileEntityHeatingMachine(bytes);
        return bytes;
    }

    private byte[] patchTileEntityHeatingMachine(byte[] bytes) {
        ClassNode classNode = new ClassNode();
        LOGGER.info("Patching class TileEntityHeatingMachine");
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);

        for (MethodNode m : classNode.methods) {
            if (m.name.equals("<init>") && m.desc.equals("(BBLic2/api/recipe/IMachineRecipeManager;II)V")) {
                Iterator<AbstractInsnNode> it = m.instructions.iterator();
                while (it.hasNext()) {
                    AbstractInsnNode insn = it.next();
                    if (insn instanceof MethodInsnNode) {
                        MethodInsnNode mInsn = (MethodInsnNode) insn;
                        for (Map.Entry<String, String> entry : REPLACEMENTS.entrySet()) {
                            if (mInsn.owner.equals(entry.getKey()) && mInsn.name.equals("<init>") && mInsn.desc.equals(entry.getValue())) {
                                mInsn.desc = mInsn.desc.replace("ic2/core/block/TileEntityInventory", "ic2/core/block/IInventorySlotHolder");
                                break;
                            }
                        }
                    }
                }
            }
        }

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        LOGGER.info("Class TileEntityHeatingMachine patched successfully!");
        return cw.toByteArray();
    }
}
