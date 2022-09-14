package com.luizbebe.staffchat.listeners;

import com.luizbebe.staffchat.Main;
import com.luizbebe.staffchat.dao.ChatDAO;
import com.luizbebe.staffchat.data.Settings;
import com.luizbebe.staffchat.group.Group;
import com.luizbebe.staffchat.methods.StaffChatMethods;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class SendMessageListener implements Listener {

    private final Settings settings;

    private final StaffChatMethods staffChatMethods;

    private final Group group;

    public SendMessageListener(Main main) {
        settings = main.getSettings();

        staffChatMethods = main.getStaffChatMethods();

        group = main.getGroup();

        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    void playerChat(AsyncPlayerChatEvent event) {
        val player = event.getPlayer();
        if (!ChatDAO.contains(player))
            return;

        val message = event.getMessage();
        staffChatMethods.sendMessage(player, message);
        event.setCancelled(true);
    }

}
