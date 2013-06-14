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
import org.spout.api.component.type.EntityComponent;
import org.spout.api.entity.Player;
import org.spout.api.geo.discrete.Point;
import org.spout.api.geo.discrete.Transform;
import org.spout.api.math.Vector3;
import org.spout.droplet.border.BorderConfiguration.BorderType;

public class BorderComponent extends EntityComponent {
	Transform lastValid;
	Player player;
	long tick = 0;
	
	@Override
	public void onAttached() {
		lastValid = getOwner().getScene().getTransform();
		player = (Player) getOwner();
	}
	
	@Override
	public void onTick(float dt) {
		tick ++;
		if (tick % 10 != 0) {
			return;
		}
		Vector3 center = (Vector3) BorderConfiguration.getCenter();
		Point pos = getOwner().getScene().getPosition();
		double radius = BorderConfiguration.RADIUS.getDouble();
		BorderType type = (BorderType) BorderConfiguration.getBorderType();
		if (!type.isInBorder(center, pos, radius)) {
			if (BorderConfiguration.ENABLED.getBoolean()) {
				player.sendMessage(ChatStyle.DARK_RED, "You shalt not pass!");
				player.getScene().setTransform(lastValid);
				player.teleport(lastValid.getPosition().add(0, 0.2, 0));
			}
		} else {
			lastValid = player.getScene().getTransform();
		}
	}
}
