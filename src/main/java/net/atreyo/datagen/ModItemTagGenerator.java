package net.atreyo.datagen;

import net.atreyo.atommod.atommod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.apache.commons.compress.compressors.lz77support.LZ77Compressor;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator  extends ItemTagsProvider {
    /**

     */
    public ModItemTagGenerator(PackOutput p , CompletableFuture<HolderLookup.Provider> future , CompletableFuture<TagLookup<Block>> tag, @Nullable ExistingFileHelper existingFileHelper){
        super(p,future,tag, atommod.MOD_ID,existingFileHelper);

    }
    @Override
    protected void addTags(HolderLookup.Provider provider) {
      // this.tag()
    }
    public String getName(){
        return "Item Tags";
    }
}
