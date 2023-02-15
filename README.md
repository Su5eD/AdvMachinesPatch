# Advanced Machines Patcher
Patches the Advanced Machines mod to work with newer IC2 builds (2.8.191+)

Fixed errors:
- `java.lang.NoSuchMethodError: ic2.core.block.invslot.InvSlotProcessableGeneric.<init>(Lic2/core/block/TileEntityInventory;Ljava/lang/String;ILic2/api/recipe/IMachineRecipeManager;)V`
- `java.lang.NoSuchMethodError: ic2.core.block.invslot.InvSlotOutput.<init>(Lic2/core/block/TileEntityInventory;Ljava/lang/String;I)V`
- `java.lang.NoSuchMethodError: ic2.core.block.invslot.InvSlotUpgrade.<init>(Lic2/core/block/TileEntityInventory;Ljava/lang/String;I)V`

Additionally, the Thermal Washer recipe now uses oredict `dustSulfur` rather than only the IC2 item.

Side note: These errors can be fixed by just re-compiling the mod with IC2 2.8.191 or newer. Because the source is not available, and the developer hasn't updated it since April 8th 2020 when the errors were first reported, I decided to make this small patcher.
