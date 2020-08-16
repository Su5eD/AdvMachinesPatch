package mods.su5ed.advmachinespatch.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class AClassTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (name.contains("com.chocohead.AdvMachines.te.TileEntityHeatingMachine"))
            return patchTileEntityHeatingMachine(bytes);
        return bytes;
    }

    private byte[] patchTileEntityHeatingMachine(byte[] bytes) {
        ClassNode classNode = new ClassNode();
        System.out.println("[AdvMachinesPatchCoreMod] Patching class TileEntityHeatingMachine");
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);

        for (MethodNode m : classNode.methods) {
            if (classNode.methods.indexOf(m) == 3) {
                System.out.println(m.instructions.get(60));
                ((MethodInsnNode)m.instructions.get(60)).desc = "(Lic2/core/block/IInventorySlotHolder;Ljava/lang/String;ILic2/api/recipe/IMachineRecipeManager;)V";
                ((MethodInsnNode)m.instructions.get(70)).desc = "(Lic2/core/block/IInventorySlotHolder;Ljava/lang/String;I)V";
                ((MethodInsnNode)m.instructions.get(80)).desc = "(Lic2/core/block/IInventorySlotHolder;Ljava/lang/String;I)V";
            }
        }

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        System.out.println("[AdvMachinesPatchCoreMod] Class TileEntityHeatingMachine patched successfully!");
        return cw.toByteArray();
    }
}
