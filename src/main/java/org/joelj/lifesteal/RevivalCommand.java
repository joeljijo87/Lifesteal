package org.joelj.lifesteal;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class RevivalCommand implements CommandExecutor, Listener {

    private Lifesteal main;

    private String name;

    private Boolean contains;
    public RevivalCommand(Lifesteal main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(player.getInventory().contains(main.revival)) {
                contains = true;
                player.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "YOU HAVE ACTIVATED THE REVIVAL TOOL! TYPE THE PERSON YOU WOULD LIKE TO REVIVE IN CHAT NOW!");
                name = player.getName();

            } else {
                contains = false;
                player.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "YOU HAVE MISUSED THIS PRIVILEGE! DO NOT MESS WITH THE GODS AGAIN!");
                player.getWorld().strikeLightning(player.getLocation());
            }

        }

        return false;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if(contains == true && e.getPlayer().getName() == name) {
            if(main.banned.contains(e.getMessage())) {
                main.banned.remove(e.getMessage());
                contains = false;
                Bukkit.getPlayer(e.getMessage()).setHealth(14.0);
            } else {
                e.getPlayer().sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "THAT USER DOES NOT EXIST ON THE BANNED LIST PLEASE TYPE ANOTHER ONE!");
            }
        }


    }

}



