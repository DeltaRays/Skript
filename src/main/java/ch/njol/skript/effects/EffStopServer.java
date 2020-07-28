/**
 * This file is part of Skript.
 *
 * Skript is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Skript is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Skript.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 * Copyright 2011-2017 Peter Güttinger and contributors
 */
package ch.njol.skript.effects;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;


@Name("Stop Server")
@Description("Stop/Restart the server. If restarting isn't configured, the server will stop.")
@Examples({"stop the server", "restart server"})
@Since("INSERT VERSION")
public class EffStopServer extends Effect {
	
	static {
		Skript.registerEffect(EffStopServer.class, "(stop|shut[ ]down) [the] server", "restart [the] server");
	}
	
	int pattern;
	
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		pattern = matchedPattern;
		return true;
	}
	
	@Override
	protected void execute(Event e) {
		switch (pattern) {
			case 0:
				Bukkit.shutdown();
				break;
			case 1:
				Bukkit.spigot().restart();
				break;
		}
	}
	
	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return (pattern == 0 ? "stop" : "restart") + " the server";
	}
	
}
