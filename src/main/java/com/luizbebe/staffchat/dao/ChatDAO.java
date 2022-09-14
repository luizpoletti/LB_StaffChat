package com.luizbebe.staffchat.dao;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChatDAO {

    @Getter
    private static final List<Player> players = new ArrayList<>();

    public static void add(Player player) {
        players.add(player);
    }

    public static void remove(Player player) {
        players.remove(player);
    }

    public static boolean contains(Player player) {
        return players.contains(player);
    }

}
