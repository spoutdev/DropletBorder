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
