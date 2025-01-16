package com.iridium.iridiumskyblock.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.cryptomorin.xseries.XMaterial;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.User;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BlockBreakListener implements Listener {
    
    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        User user = IridiumSkyblock.getInstance().getUserManager().getUser(event.getPlayer());
        XMaterial material = XMaterial.matchXMaterial(event.getBlock().getType());

        try {
            Island island = user.getCurrentIsland().get();
            if (island.isRedstone(material)) {
                island.decrementRedstone();
            }
        } catch (Exception e) {
            // Bukkit.broadcastMessage("No Island: Break");
            return;
        }
    }

}
