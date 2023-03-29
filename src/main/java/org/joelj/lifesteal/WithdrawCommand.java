package org.joelj.lifesteal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WithdrawCommand implements CommandExecutor {

    private Lifesteal main;

    public WithdrawCommand(Lifesteal main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length == 1) {

                player.getInventory().setItem(Integer.parseInt(args[0]), main.heart);
                player.setHealth(player.getHealth() - Integer.parseInt(args[0]));

            }


        }

        return false;
    }
}
