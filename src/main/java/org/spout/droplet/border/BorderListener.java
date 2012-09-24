package org.spout.droplet.border;

import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.entity.EntityMoveEvent;
import org.spout.api.math.Vector3;
import org.spout.droplet.border.BorderConfiguration.BorderType;

public class BorderListener implements Listener {
	@EventHandler
	public void onPlayerMove(EntityMoveEvent event) {
		if (event.getEntity() instanceof Player) {
			if (BorderConfiguration.ENABLED.getBoolean()) {
				Vector3 center = (Vector3) BorderConfiguration.CENTER.getValue();
				Vector3 pos = event.getTo();
				double radius = BorderConfiguration.RADIUS.getDouble();
				BorderType type = (BorderType) BorderConfiguration.BORDER_TYPE.getValue();
				if (!type.isInBorder(center, pos, radius)) {
					event.setCancelled(true);
					event.getEntity().getTransform().setPosition(event.getFrom());
				}
			}
		}
	}
}
