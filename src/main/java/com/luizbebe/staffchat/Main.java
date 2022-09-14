package com.luizbebe.staffchat;

import com.luizbebe.staffchat.commands.StaffChatCommand;
import com.luizbebe.staffchat.data.Settings;
import com.luizbebe.staffchat.group.Group;
import com.luizbebe.staffchat.group.permissions.LP;
import com.luizbebe.staffchat.group.permissions.Pex;
import com.luizbebe.staffchat.listeners.QuitListener;
import com.luizbebe.staffchat.listeners.SendMessageListener;
import com.luizbebe.staffchat.methods.StaffChatMethods;
import com.luizbebe.staffchat.setup.SettingsSetup;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Main extends JavaPlugin {

    private Settings settings;

    private StaffChatMethods staffChatMethods;

    private Group group;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage("§b[" + getName() + "] §fPlugin started");
        Bukkit.getConsoleSender().sendMessage("§b[" + getName() + "] §fEnter my discord server:");
        Bukkit.getConsoleSender().sendMessage("§b[" + getName() + "] §bhttps://discord.gg/j8f8aHvcyc");
        registerObjects();
        register();
    }

    private void register() {
        new StaffChatCommand(this);
        new SendMessageListener(this);
        new QuitListener(this);
    }

    private void registerObjects() {
        settings = new SettingsSetup(this).setup();

        group = Bukkit.getPluginManager().getPlugin("LuckPerms") == null ? new Pex(this) : new LP(this);

        staffChatMethods = new StaffChatMethods(this);
    }

}
