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
	private static final ConfigurationHolder BORDER_TYPE = new ConfigurationHolder("SQUARE", "border-type");
	private static final ConfigurationHolder CENTER_X = new ConfigurationHolder(0d, "center-x");
	private static final ConfigurationHolder CENTER_Z = new ConfigurationHolder(0d, "center-z");
	private static Vector3 center = null;
	private static BorderType borderType = null;
	
	
	public static BorderType getBorderType() {
		if (borderType == null) {
			borderType = BorderType.valueOf(BORDER_TYPE.getString());
		}
		return borderType;
	}

	public static void setBorderType(BorderType borderType) {
		BORDER_TYPE.setValue(borderType.toString());
		BorderConfiguration.borderType = borderType;
	}

	public static Vector3 getCenter() {
		if (center == null) {
			center = new Vector3(CENTER_X.getDouble(), 0, CENTER_Z.getDouble());
		}
		return center;
	}
	
	public static void setCenter(Vector3 center) {
		CENTER_X.setValue(center.getX());
		CENTER_Z.setValue(center.getZ());
		center = new Vector3(center);
	}
	
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
