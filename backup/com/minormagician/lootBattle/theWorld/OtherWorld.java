package com.minormagician.lootBattle.theWorld;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.minormagician.lootBattle.lootBattle;
import com.minormagician.lootBattle.things.SpriteFinder;
import com.minormagician.lootBattle.things.Thing;

public class OtherWorld {
	
	
	// Sets the final varaibles.
	final static int MAP_LAYERS = 2;
	final static int PLAYER_LAYER= 1;
	final static int MAP_SIZE = 20;
	final static int TILE_SIZE = 40;
	final static int SCREEN_X_TILES = 20;
	final static int SCREEN_Y_TILES = 15;
	
	
	//Minimap location and scale
	public static final int MARKER_SIZE = 20;
	public static final int MINIMAP_LEFT = 0;
	public static final int MINIMAP_RIGHT = 100;
	public static final int MINIMAP_TOP = 1150;
	public static final int MINIMAP_BOTTOM = 0;
	final static int SCALE = 4;
	
	// Declares a main game class though I don't use it yet.
	lootBattle game;
	
	// Declares the cameras
	OrthographicCamera camera;
	SpriteBatch batch;
	
	// Declares minimap camera
	OrthographicCamera miniMapCam;
	SpriteBatch miniBatch;
	
	
	// Creates the can move boolean, pretty self explanatory really
	static boolean canMove = false;
	static boolean miniMap = false;
	
	// Declares the player and the movement variables
	static public Vector2 playerPos;
	public Thing newPlayerPos;
	static public Thing player;
	public float direction;
	
	// Declares the map
	static Thing[][][] map;
	
	public OtherWorld(lootBattle lootBattle) {
		this.game = lootBattle;
		}
	
	public void createWorld() {
		
		// First we create the player -- right now in the middle of the map, but the commented out code will load near the bottom left corner
		player = new Thing((MAP_SIZE * TILE_SIZE) / 2, (MAP_SIZE * TILE_SIZE) / 2, SpriteFinder.playerId);
//		player = new Thing(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, TILE_SIZE, TILE_SIZE, SpriteFinder.playerId);
		playerPos = new Vector2(player.x, player.y);
		newPlayerPos = new Thing(player);
		direction = playerPos.angle();
		
		// then we create the camera and sprite batch and link them
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		
		// Then we do the same for them minimap, multiply width and height to make it smaller
		miniMapCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    miniMapCam.zoom = SCALE;
	    miniBatch = new SpriteBatch();
		
	    
	    //this is map generation such as it is
	    
		//first we create the map array
		 map = new Thing[MAP_LAYERS][MAP_SIZE][MAP_SIZE];
		
		//then we loop through that array to place tiles
		for(int x = 0; x < MAP_SIZE; x++){
			for(int y = 0; y < MAP_SIZE; y++){
				
				//First the z level below the player, player layer is one by default
				
				//the randomId gets id number for ground level items, which are between 1 and 10. presently there are two
				int randomId = MathUtils.random(SpriteFinder.terrainStart, SpriteFinder.terrainEnd);
				//it takes the randomId and gets creates a thing of it.
				map[PLAYER_LAYER-1][x][y] = new Thing(x * TILE_SIZE, y * TILE_SIZE, SpriteFinder.getId(randomId));

				//player z level fills with air
				map[PLAYER_LAYER][x][y] = new Thing(x * TILE_SIZE, y * TILE_SIZE, SpriteFinder.airId);
				//then it generates a wall around the edge
				if(x == 0 || y == 0 || x == MAP_SIZE-1 || y == MAP_SIZE-1){
					map[PLAYER_LAYER][x][y] = new Thing(x * TILE_SIZE, y * TILE_SIZE, SpriteFinder.wallId);
					
				}
			}
			
		}
		
		//then we add some stuff to the map - ls stands for landscape and it will make around 10 of them
		for(int ls = 0; ls < 10; ls++){
			int randomX = MathUtils.random(MAP_SIZE -1);
			int randomY = MathUtils.random(MAP_SIZE - 1);
			//if its a wall tile it is the edge so skip it
			if(map[1][randomX][randomY].id != SpriteFinder.wallId){
				
				int randomId = MathUtils.random(SpriteFinder.landscapeStart, SpriteFinder.landscapeEnd);
				map[PLAYER_LAYER][randomX][randomY] = new Thing(randomX * TILE_SIZE, randomY * TILE_SIZE, SpriteFinder.getId(randomId));
				//if the player is there change it to air
				if(player.overlaps(map[1][randomX][randomY])){ 
					map[PLAYER_LAYER][randomX][randomY] = new Thing(randomX * TILE_SIZE, randomY * TILE_SIZE, TILE_SIZE, TILE_SIZE, SpriteFinder.airId);
				}
			}
		}
		
	}
	
	public void render(){
		
		
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//set up the camera
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		camera.position.set(playerPos.x, playerPos.y, 0);
		
		//draw the tiles
		batch.begin();
		
		//loop through the map
		for(int x = 0; x < MAP_SIZE; x++){
			for(int y = 0; y < MAP_SIZE; y++){
				
//				and draws the z level below the player
				batch.draw(map[PLAYER_LAYER-1][x][y].sprite, map[PLAYER_LAYER-1][x][y].x, map[PLAYER_LAYER-1][x][y].y);
				
				//and anything on the players level that isn't air
				if(map[PLAYER_LAYER][x][y].id != SpriteFinder.airId){
					batch.draw(map[PLAYER_LAYER][x][y].sprite, map[1][x][y].x, map[PLAYER_LAYER][x][y].y);
				}
			}
		}
		
		//then draw the player
		batch.draw(player.sprite, player.x, player.y, player.x / 2, player.y / 2, player.width, player.height, 1, 1, player.sprite.getRotation());
		
		batch.end();
		
		//if minimap is true then generate the minimap
		if(miniMap){
		miniMapCam.update();
		miniBatch.setProjectionMatrix(miniMapCam.combined);
			
		miniBatch.begin();
		
		for(int x = 0; x < MAP_SIZE; x++){
			for(int y = 0; y < MAP_SIZE; y++){
				
				miniBatch.draw(map[0][x][y].sprite, map[0][x][y].x - (Gdx.graphics.getWidth()/2)*SCALE + ((MINIMAP_RIGHT-MINIMAP_LEFT)/2)*SCALE
						, map[0][x][y].y + (Gdx.graphics.getHeight()/2)*SCALE - ((MINIMAP_TOP-MINIMAP_BOTTOM)/2)*SCALE);
				
				if(map[1][x][y].id != SpriteFinder.airId){
					miniBatch.draw(map[1][x][y].sprite, map[1][x][y].x - (Gdx.graphics.getWidth()/2)*SCALE + ((MINIMAP_RIGHT-MINIMAP_LEFT)/2)*SCALE
							, map[1][x][y].y + (Gdx.graphics.getHeight()/2)*SCALE - ((MINIMAP_TOP-MINIMAP_BOTTOM)/2)*SCALE);
				}
			}
		}
		
		miniBatch.draw(player.sprite, player.x - (Gdx.graphics.getWidth()/2)*SCALE + ((MINIMAP_RIGHT-MINIMAP_LEFT)/2)*SCALE, 
				player.y + (Gdx.graphics.getHeight()/2)*SCALE - ((MINIMAP_TOP-MINIMAP_BOTTOM)/2)*SCALE);
		
		miniBatch.end();
		}
		
		//if it's true and you're not holding the button it should be false
		if(miniMap) miniMap = false;
		//assumes you can move and then turn it to false if you can;t
		canMove = true;
		//find out if you're moving
		move();
		
	}
	
	public void move(){
		
		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)){
			//it moves the ghost newPlayerPos where you want to go and finds out if it can move
			newPlayerPos.set(player);
			newPlayerPos.x -= 1;
			setcanMove();
			
			//if it can you move there
			if (canMove){
			playerPos.x -= 1;
			player.x = playerPos.x;
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)){ 
			
			newPlayerPos.set(player);
			newPlayerPos.x += 1;
			setcanMove();
			
			if (canMove){
			playerPos.x += 1;
			player.x = playerPos.x;
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)){ 
			
			newPlayerPos.set(player);
			newPlayerPos.y += 1;
			setcanMove();
			
			if (canMove){
			playerPos.y += 1;
			player.y = playerPos.y;
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)){ 
			
			newPlayerPos.set(player);
			newPlayerPos.y -= 1;
			setcanMove();
			
			if (canMove){
			playerPos.y -= 1;
			player.y = playerPos.y;
			}
		}
		
		//loads the minimap if you press m
		if(Gdx.input.isKeyPressed(Keys.M)){ 
			
			if(!miniMap) miniMap = true;
			}
		
	}
		
		
		
	public void setcanMove(){
		
		//this loops through the map and checks to see if the player is going to hit something
		for (int x = 0; x < MAP_SIZE; x++){
			for (int y = 0; y < MAP_SIZE; y++){
				if (!map[1][x][y].passable && newPlayerPos.overlaps(map[1][x][y])){
					canMove = false;
				}
			}
		}
		
		//then resets the newPlayer position to check again
		newPlayerPos.set(player);
	}
		
	public void dispose(){
		SpriteFinder.dispose();
		batch.dispose();
	}
	
}
