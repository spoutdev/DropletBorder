/*
 * This file is part of DropletBorder.
 *
 * Copyright (c) 2012 Spout LLC <http://www.spout.org/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.spout.droplet.border;

import org.spout.api.chat.style.ChatStyle;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.Command;
import org.spout.api.command.annotated.CommandPermissions;
import org.spout.api.entity.Player;
import org.spout.api.geo.discrete.Point;
import org.spout.droplet.border.BorderConfiguration;
import org.spout.droplet.border.BorderConfiguration.BorderType;

public class BorderCommands {
	DropletBorder plugin;

	public BorderCommands(DropletBorder plugin) {
		this.plugin = plugin;
	}
	
	@Command(aliases = {"border"}, usage = "enable|disable|set [radius] [square|circle]", desc="controls the border")
	@CommandPermissions("dropletborder.control")
	public void commandRoot(CommandContext args, CommandSource source) {
		if (args.length() >= 1) {
			String mode = args.getString(0);
			if (mode.equals("enable")) {
				BorderConfiguration.ENABLED.setValue(true);
				source.sendMessage(ChatStyle.DARK_GREEN, "Border enabled.");
			} else if (mode.equals("disable")) {
				BorderConfiguration.ENABLED.setValue(false);
				source.sendMessage(ChatStyle.DARK_GREEN, "Border disabled.");
			} else if (mode.equals("set")) {
				if (args.length() >= 3) {
					if (source instanceof Player) {
						Player p = (Player) source;
						double radius = args.getDouble(1);
						BorderType type = BorderType.valueOf(args.getString(2).toUpperCase());
						Point center = p.getScene().getPosition();
						BorderConfiguration.setCenter(center);
						BorderConfiguration.setBorderType(type);
						BorderConfiguration.RADIUS.setValue(radius);
						source.sendMessage(ChatStyle.DARK_GREEN, "Border set successfully.");
						sendinfo(source);
					} else {
						source.sendMessage(ChatStyle.DARK_RED, "This works ingame only.");
					}
				} else {
					source.sendMessage(ChatStyle.DARK_RED, "Not enough arguments for set (/border set <radius> <square|circle>).");
				}
			}
		} else {
			// Display information
			sendinfo(source);
		}
	}

	private void sendinfo(CommandSource source) {
		source.sendMessage(ChatStyle.YELLOW, "Border: ", ChatStyle.WHITE, (BorderConfiguration.ENABLED.getBoolean() ? "enabled" : "disabled"));
		source.sendMessage(ChatStyle.YELLOW, "Radius: ", ChatStyle.WHITE, BorderConfiguration.RADIUS.getInt(), ChatStyle.YELLOW, " Type: ", ChatStyle.WHITE, BorderConfiguration.getBorderType().toString().toLowerCase());
	}
}
