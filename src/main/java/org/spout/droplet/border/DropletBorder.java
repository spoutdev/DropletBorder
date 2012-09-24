package org.spout.droplet.border;

import java.util.logging.Level;

import org.spout.api.UnsafeMethod;
import org.spout.api.command.CommandRegistrationsFactory;
import org.spout.api.command.annotated.AnnotatedCommandRegistrationFactory;
import org.spout.api.command.annotated.SimpleAnnotatedCommandExecutorFactory;
import org.spout.api.command.annotated.SimpleInjector;
import org.spout.api.plugin.CommonPlugin;

public class DropletBorder extends CommonPlugin {
	private BorderConfiguration config;

	@Override
	@UnsafeMethod
	public void onEnable() {
		config = new BorderConfiguration(this);
		config.load();
		CommandRegistrationsFactory<Class<?>> commandRegFactory = new AnnotatedCommandRegistrationFactory(new SimpleInjector(this), new SimpleAnnotatedCommandExecutorFactory());
		getEngine().getRootCommand().addSubCommands(this, BorderCommands.class, commandRegFactory);
		getEngine().getEventManager().registerEvents(new BorderListener(), this);
		log("DropletBorder enabled");
	}

	@Override
	@UnsafeMethod
	public void onDisable() {
		config.save();
		log("DropletBorder disabled");
	}
	
	public void log(String text) {
		log(Level.INFO, text);
	}
	
	public void log(Level level, String text) {
		getLogger().log(level, text);
	}

}
