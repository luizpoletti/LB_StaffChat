package com.luizbebe.staffchat.methods;

import com.luizbebe.staffchat.Main;
import com.luizbebe.staffchat.dao.ChatDAO;
import com.luizbebe.staffchat.data.Messages;
import com.luizbebe.staffchat.data.Settings;
import com.luizbebe.staffchat.group.Group;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class StaffChatMethods {

    private final Settings settings;
    private final Messages messages;

    private final Group group;

    public StaffChatMethods(Main main) {
        settings = main.getSettings();
        messages = settings.getMessages();

        group = main.getGroup();
    }

    public void changeState(Player player) {
        if (ChatDAO.contains(player)) {
            player.sendMessage(messages.getQuit());
            player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1F, 1F);
            ChatDAO.remove(player);
            return;
        }
        player.sendMessage(messages.getJoin());
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);
        ChatDAO.add(player);
    }

    public void sendMessage(Player player, String message) {
        val format = settings.getFormat().replace("{group}", group.getGroup(player)).replace("{sender}", player.getName()).replace("{message}", message);
        Bukkit.getOnlinePlayers().stream().filter(players -> players.hasPermission("lbchatstaff.see")).forEach(players -> {
            if (settings.isSendSound())
                players.playSound(players.getLocation(), settings.getSound(), 1F, 1F);

            if (settings.isHighlightWithPerm() && player.hasPermission("lbchatstaff.highlight")) {
                players.sendMessage("");
                players.sendMessage(format);
                players.sendMessage("");
                return;
            }
            players.sendMessage(format);
        });
    }

}
