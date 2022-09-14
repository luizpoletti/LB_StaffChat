package com.luizbebe.staffchat.listeners;

import com.luizbebe.staffchat.Main;
import com.luizbebe.staffchat.dao.ChatDAO;
import com.luizbebe.staffchat.data.Settings;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    private final Settings settings;

    public QuitListener(Main main) {
        settings = main.getSettings();

        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    void playerQuit(PlayerQuitEvent event) {
        val player = event.getPlayer();
        if (settings.isRemoveOnExit())
            if (ChatDAO.contains(player))
                ChatDAO.remove(player);
    }

}
