package com.minormagician.lootBattle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.minormagician.lootBattle.theWorld.World;
//import com.minormagician.lootBattle.theWorld.OtherWorld;

public class lootBattle extends Game {
	
	FPSLogger log;
	public static final String LOG = "Action";
//	OtherWorld world = new OtherWorld(this);
	World world = new World(this);
		
	@Override
	public void create() {
		Gdx.app.log(lootBattle.LOG, "Load and start FPS Logger");
		log = new FPSLogger();
		world.createWorld();
		
	}

	@Override
	public void dispose() {
		world.dispose();
	}

	@Override
	public void render() {
//		super.render();
		world.render();
		log.log();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
