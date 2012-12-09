package com.minormagician.lootBattle.theWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.minormagician.lootBattle.lootBattle;
import com.minormagician.lootBattle.things.SpriteFinder;
import com.minormagician.lootBattle.things.Thing;

public class World {

	// Sets the final varaibles.
	final static int MAP_LAYERS = 2;
	final static int PLAYER_LAYER= 1;
	final static int MAP_SIZE = 20;


	//Minimap scale
    final static int SCALE = 4;

	// Declares a main game class though I don't use it yet.
	lootBattle game;

	// Declares the cameras
	public static OrthographicCamera camera;
    public static Stage stage;

    // Declares minimap camera
    OrthographicCamera miniMapCam;
    Stage miniMap;

    public static Vector2 touched;
    public static Rectangle isTouched = new Rectangle(0, 0, 40, 40);

    // Creates the booleans for movment and minimap
	static boolean canMove = false;
	static boolean displayMiniMap = false;

	// Declares the player and the movement variables
	static public Vector2 playerPos;
	public Rectangle newPlayerPos;
	static public Thing player;
    static public Thing miniPlayer;
	public float direction;

//    Input in;

	// Declares the map
	static Thing[][][] map;

	public World(lootBattle lootBattle) {
		this.game = lootBattle;
	}

	public void createWorld() {


//        input.setInputProcessor(in);
        // First we create the player -- right now in the middle of the map
		player = new Thing(SpriteFinder.playerId);
        player.setXY(MAP_SIZE * player.getWidth() / 2, MAP_SIZE * player.getHeight() / 2);
        playerPos = new Vector2(player.getX(), player.getY());
        direction = playerPos.angle();
        touched = new Vector2(0, 0);
//        isTouched = newPlayerPos;


		// then we create the camera and stage and link them
		camera = new OrthographicCamera();
        stage = new Stage();
		camera.setToOrtho(false, graphics.getWidth(), graphics.getHeight());
		camera.update();
        stage.setCamera(camera);

		// Then we do the same for them minimap
		miniMapCam = new OrthographicCamera(graphics.getWidth(), graphics.getHeight());
		miniMapCam.zoom = SCALE;
		miniMap = new Stage();
        miniMap.setCamera(miniMapCam);


		//this is map generation such as it is

		//first we create the map array
		map = new Thing[MAP_LAYERS][MAP_SIZE][MAP_SIZE];

		//then we loop through that array to place tiles
		for(int x = 0; x < MAP_SIZE; x++){
			for(int y = 0; y < MAP_SIZE; y++){

				//First the z level below the player, player layer is one by default

				//the randomId gets id number for ground level items, which are between 1 and 10. presently there are two
				int randomId[] = SpriteFinder.getId(MathUtils.random(SpriteFinder.terrainStart, SpriteFinder.terrainEnd));
				//it takes the randomId and creates a thing of it.
                stage.addActor(map[PLAYER_LAYER-1][x][y] = new Thing(randomId));

                //then it sets the position
                map[PLAYER_LAYER-1][x][y].setXY(x * map[PLAYER_LAYER-1][x][y].getWidth(), y * map[PLAYER_LAYER - 1][x][y].getHeight());

                miniMap.addActor(map[PLAYER_LAYER-1][x][y].getMini());

				//player z level fills with air
                stage.addActor(map[PLAYER_LAYER][x][y] = new Thing(SpriteFinder.airId));

                map[PLAYER_LAYER][x][y].setXY(x * map[PLAYER_LAYER][x][y].getWidth(), y * map[PLAYER_LAYER][x][y].getHeight());

                miniMap.addActor(map[PLAYER_LAYER][x][y].getMini());

				//then it generates a wall around the edge
				if(x == 0 || y == 0 || x == MAP_SIZE-1 || y == MAP_SIZE-1){
                    stage.addActor(map[PLAYER_LAYER][x][y] = new Thing(SpriteFinder.wallId));

                    map[PLAYER_LAYER][x][y].setXY(x * map[PLAYER_LAYER][x][y].getWidth(), y * map[PLAYER_LAYER][x][y].getHeight());
                    miniMap.addActor(map[PLAYER_LAYER][x][y].getMini());
				}
			}

		}

		//then we add some stuff to the map - ls stands for landscape and it will make around 10 of them
		for(int ls = 0; ls < 10; ls++){
			int randomX = MathUtils.random(MAP_SIZE - 1);
			int randomY = MathUtils.random(MAP_SIZE - 1);
			//if its a wall tile it is the edge so skip it
			if(map[1][randomX][randomY].id != SpriteFinder.wallId){

				int randomId[] = SpriteFinder.getId(MathUtils.random(SpriteFinder.landscapeStart, SpriteFinder.landscapeEnd));
                stage.addActor(map[PLAYER_LAYER][randomX][randomY] = new Thing(randomId, randomX * map[PLAYER_LAYER][randomX][randomY].getHeight(), randomY * map[PLAYER_LAYER][randomX][randomY].getWidth()));
                //if it's not passable and the player is there change it to air
				if(!map[PLAYER_LAYER][randomX][randomY].passable && player.getBoundingRectangle().overlaps(map[1][randomX][randomY].getBoundingRectangle())){
					map[PLAYER_LAYER][randomX][randomY] = new Thing(SpriteFinder.airId);
				}
                miniMap.addActor(map[PLAYER_LAYER][randomX][randomY].getMini());
			}
		}

        //finally we add the player to the stage, otherwise it draws under everything. someday I'll figure out how to bring it to the front but until then
        stage.addActor(player);
        miniMap.addActor(miniPlayer = player.getMini());
    }

	public void render(){


		gl.glClearColor(0, 0, 0.2f, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		//set up the camera
		camera.update();

        camera.position.set(playerPos.x, playerPos.y, 0);
        miniMapCam.position.set(Gdx.graphics.getWidth() + 700, Gdx.graphics.getHeight() + 550, 0);
        stage.draw();

        if(displayMiniMap){
        miniPlayer.setXY(player.getX(), player.getY());
        miniMap.draw();
        }

        //if it's true and you're not holding the button it should be false
		if(displayMiniMap) displayMiniMap = false;

		//assumes you can move and then turn it to false if you can't
		canMove = true;
		//find out if you're trying moving
		move();


	}

	public void move(){

		if(input.isKeyPressed(Keys.LEFT) || input.isKeyPressed(Keys.A)){
			//it moves the ghost newPlayerPos where you want to go and finds out if it can move

			newPlayerPos = player.getBoundingRectangle();
			newPlayerPos.x -= 1;
			setcanMove();

			//if it can you move there
			if (canMove){
				playerPos.x -= 1;
				player.setX(playerPos.x);
			}
		}

		if(input.isKeyPressed(Keys.RIGHT) || input.isKeyPressed(Keys.D)){

            newPlayerPos = player.getBoundingRectangle();
			newPlayerPos.x += 1;
			setcanMove();

			if (canMove){
				playerPos.x += 1;
                player.setX(playerPos.x);
			}
		}

		if(input.isKeyPressed(Keys.UP) || input.isKeyPressed(Keys.W)){

            newPlayerPos = player.getBoundingRectangle();
			newPlayerPos.y += 1;
			setcanMove();

			if (canMove){
				playerPos.y += 1;
                player.setY(playerPos.y);
			}
		}

		if(input.isKeyPressed(Keys.DOWN) || input.isKeyPressed(Keys.S)){

            newPlayerPos = player.getBoundingRectangle();
			newPlayerPos.y -= 1;
			setcanMove();

			if (canMove){
				playerPos.y -= 1;
                player.setY(playerPos.y);
			}
		}

		//loads the minimap if you press m
		if(input.isKeyPressed(Keys.M)){
            if(!displayMiniMap) displayMiniMap = true;
		}
//
        if(input.isTouched()){
//
            for(int x = 0; x < MAP_SIZE; x++){
                for(int y = 0; y < MAP_SIZE; y++){
                    touched.x = input.getX();
                    touched.y = input.getY();
                    stage.screenToStageCoordinates(touched);
                    isTouched = new Rectangle(touched.x, touched.y, 40, 40);
//                    if(map[1][x][y].id == SpriteFinder.treeId && isTouched.overlaps(map[1][x][y].getBoundingRectangle())){

                    app.log(lootBattle.LOG, "you touched  " + map[1][x][y].id);

//                  }
                }
            }
        }

       }

    public void setcanMove(){

		//this loops through the map and checks to see if the player is going to hit something
		for (int x = 0; x < MAP_SIZE; x++){
			for (int y = 0; y < MAP_SIZE; y++){
                if (!map[1][x][y].passable && newPlayerPos.overlaps(map[1][x][y].getBoundingRectangle())){
//                    Gdx.app.log(lootBattle.LOG, "can't move bro");
                    canMove = false;
				}
			}
		}
	}

	public void dispose(){
		SpriteFinder.dispose();
		stage.dispose();
	}
}
