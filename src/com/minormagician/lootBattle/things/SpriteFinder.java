package com.minormagician.lootBattle.things;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteFinder {
	
	//this is the id look ups, first is id second is passable 0 is true, 1 is false - the int are for map generation
	public static final int[] voidId = {-2, 0};
	public static final int[] airId = {-1, 0};
	public static final int[] playerId = {0, 0};
	
	//terrain id 1 - 50
	public static final int terrainStart = 1;
	public static final int[] grassId = {1, 0};
	public static final int[] stoneId = {2, 0};
	
	public static final int terrainEnd = 2;
	//currently there are two but there is room to expand
//	public static final int terrainEnd = 49;
	
	//landscape id's 50 - 49
	public static final int landscapeStart = 50;
	public static final int[] bushId = {50, 0};
	public static final int[] treeId = {51, 1};
	public static final int[] boulderId = {52, 1};
	
	public static final int landscapeEnd = 52;
//	public static final int landscapeEnd = 59;
	
	//walls are 60 - 99
	public static final int wallStart = 60;
	public static final int[] wallId = {60, 1};
	
	public static final int wallEnd = 60;
//	public static final int wallEnd = 99;
	
	
	// items are 100 - 199
	public static final int itemStart = 100;
	public static final int[] chestId = {100, 0};
	
	public static final int itemEnd = 100;
//	public static final int itemEnd = 199;
	
//	public static final int[]
//	public static final int[]
//	public static final int[]
//	public static final int[]
//	public static final int[]
	
	//Enemies start at id 200 - 299
	public static final int enemyStart = 200;
	public static final int[] robotEnemyId = {200, 0};
	public static final int[] goldenDragonId = {201, 0};
	public static final int[] ironTrollId = {202,0};
	
	public static final int enemyEnd = 202;
//	public static final int enemyEnd = 299;
	
//	public static final int[]
//	public static final int[]
//	public static final int[]
	
	//loads the atlas and sprite locations
	public static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/lootAtlas.atlas"));
	public static final TextureRegion playerTile = atlas.findRegion("playerUp");
	public static final TextureRegion airTile = atlas.findRegion("air");
	public static final TextureRegion robotEnemyTile = atlas.findRegion("robotIcon");
	public static final TextureRegion grassTile = atlas.findRegion("grass");
	public static final TextureRegion wallTile = atlas.findRegion("wall");
	public static final TextureRegion boulderTile = atlas.findRegion("boulder");
	public static final TextureRegion chestTile = atlas.findRegion("chest");
	public static final TextureRegion goldenDragonTile = atlas.findRegion("golden_dragon");
	public static final TextureRegion stoneTile = atlas.findRegion("stone");
	public static final TextureRegion treeTile = atlas.findRegion("tree");
	public static final TextureRegion ironTrollTile = atlas.findRegion("iron_troll");
	public static final TextureRegion bushTile = atlas.findRegion("bush");

	//when given an id returns the sprite
	public static TextureRegion getSprite(int[] id){
		
		if(id[0] == airId[0]) 
			return airTile;
		
		if(id[0] == grassId[0])
			return grassTile;
		
		if(id[0] == wallId[0])
			return wallTile;
			
		if(id[0] == playerId[0])
			return playerTile;
			
		if(id[0] == stoneId[0])
			return stoneTile;
			
		if(id[0] == bushId[0])
			return bushTile;
		
		if(id[0] == boulderId[0])
			return boulderTile;
			
		if(id[0] == treeId[0])
			return treeTile;
			
		if(id[0] == chestId[0])
			return chestTile;
			
		if(id[0] == robotEnemyId[0])
			return robotEnemyTile;
			
		if(id[0] == goldenDragonId[0])
			return goldenDragonTile;
			
		if(id[0] == ironTrollId[0])
			return ironTrollTile;
		
		
		return airTile;
	}
	
	//when given an id return id[], used for world generation
	public static int[] getId(int id){
		
		if(id == airId[0]) return airId;
		
		if(id == grassId[0]) return grassId;
		
		if(id == wallId[0]) return wallId;
		
		if(id == playerId[0]) return playerId;
		
		if(id == stoneId[0]) return stoneId;
		
		if(id == bushId[0]) return bushId;
			
		if(id == boulderId[0]) return boulderId;
			
		if(id == treeId[0]) return treeId;
		
		if(id == chestId[0]) return chestId;
		
		if(id == robotEnemyId[0]) return robotEnemyId;
		
		if(id == goldenDragonId[0]) return goldenDragonId;
		
		if(id == ironTrollId[0]) return ironTrollId;
		
		return voidId;
	}
	
	//disposes of the atlas
	public static void dispose(){
		atlas.dispose();
	}
	
	

}
