package com.luizbebe.staffchat.commands;

import com.luizbebe.staffchat.Main;
import com.luizbebe.staffchat.data.Messages;
import com.luizbebe.staffchat.methods.StaffChatMethods;
import lombok.val;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand implements CommandExecutor {

    private final Messages messages;

    private final StaffChatMethods staffChatMethods;

    public StaffChatCommand(Main main) {
        val settings = main.getSettings();
        messages = settings.getMessages();

        staffChatMethods = main.getStaffChatMethods();

        main.getCommand("staffchat").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender)
            return true;

        val player = (Player) sender;
        if (!player.hasPermission("lbchatstaff.talk")) {
            player.sendMessage("§cVocê não tem permissão para isto.");
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 1F, 1F);
            return true;
        }
        if (args.length == 0) {
            if (player.hasPermission("lbchatstaff.enter"))
                staffChatMethods.changeState(player);

            else {
                player.sendMessage(messages.getNoArgs());
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1F, 1F);
            }
            return true;
        }
        val builder = new StringBuilder();
        for (int index = 0; index < args.length; index++)
            builder.append(args[index]).append(" ");

        val message = builder.toString();
        staffChatMethods.sendMessage(player, message);
        return false;
    }
}
