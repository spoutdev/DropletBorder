package org.spout.droplet.border;

import java.io.File;

import org.spout.api.exception.ConfigurationException;
import org.spout.api.geo.discrete.Point;
import org.spout.api.math.Vector3;
import org.spout.api.util.config.ConfigurationHolder;
import org.spout.api.util.config.ConfigurationHolderConfiguration;
import org.spout.api.util.config.yaml.YamlConfiguration;

public class BorderConfiguration extends ConfigurationHolderConfiguration {
	public static final ConfigurationHolder ENABLED = new ConfigurationHolder(false, "enabled");
	public static final ConfigurationHolder RADIUS = new ConfigurationHolder(-1d, "radius");
	public static final ConfigurationHolder BORDER_TYPE = new ConfigurationHolder(BorderType.SQUARE, "border-type");
	public static final ConfigurationHolder CENTER = new ConfigurationHolder(Point.ZERO, "center");
	
	public static enum BorderType {
		CIRCLE, SQUARE;
		
		public boolean isInBorder(Vector3 center, Vector3 toCheck, double radius) {
			double dx = center.getX() - toCheck.getX();
			double dz = center.getZ() - toCheck.getZ();
			switch(this) {
			case CIRCLE:
				return dx * dx + dz * dz <= radius * radius;
			case SQUARE:
				return Math.abs(dx) <= radius && Math.abs(dz) <= radius;
			}
			return false;
		}
	}
	
	public BorderConfiguration(DropletBorder plugin) {
		super(new YamlConfiguration(new File(plugin.getDataFolder(), "config.yml")));
	}
	
	@Override
	public void load() {
		try {
			super.load();
			super.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save() {
		try {
			super.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
}
