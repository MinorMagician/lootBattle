package com.minormagician.lootBattle.things;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class SpriteFinder {
	
	//this is the id look ups, first is id second is passable 0 is true, 1 is false - the int are for map generation
	public static final int[] voidId = {-2, 0};
	public static final int[] airId = {-1, 0};
	public static final int[] playerId = {0, 0};
	
	//terrain id 1 - 9
	public static final int terrainStart = 1;
	public static final int[] grassId = {1, 0};
	public static final int[] stoneId = {2, 0};
	
	public static final int terrainEnd = 2;
	//currently there are two but there is room to expand
//	public static final int terrainEnd = 9;
	
	//landscape id's 10 - 29
	public static final int landscapeStart = 10;
	public static final int[] bushId = {10, 0};
	public static final int[] treeId = {11, 1};
	public static final int[] boulderId = {12, 1};
	
	public static final int landscapeEnd = 12;
//	public static final int landscapeEnd = 29;
	
	//walls are 30 - 39
	public static final int wallStart = 30;
	public static final int[] wallId = {30, 1};
	
	public static final int wallEnd = 30;
//	public static final int wallEnd = 39;
	
	
	// items are 40 -99
	public static final int itemStart = 40;
	public static final int[] chestId = {40, 0};
	
	public static final int itemEnd = 40;
//	public static final int itemEnd = 99;
	
//	public static final int[]
//	public static final int[]
//	public static final int[]
//	public static final int[]
//	public static final int[]
	
	//Enemies start at id 100 - 199
	public static final int enemyStart = 100;
	public static final int[] robotEnemyId = {100, 0};
	public static final int[] goldenDragonId = {101, 0};
	public static final int[] ironTrollId = {102,0};
	
	public static final int enemyEnd = 102;
//	public static final int enemyEnd = 199;
	
//	public static final int[]
//	public static final int[]
//	public static final int[]
	
	//loads the atlas and sprite locations
	public static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/lootAtlas.atlas"));
	public static final Sprite playerTile = new Sprite(atlas.findRegion("playerUp"));
	public static final Sprite airTile = new Sprite(atlas.findRegion("air"));
	public static final Sprite robotEnemyTile = new Sprite(atlas.findRegion("robotIcon"));
	public static final Sprite grassTile = new Sprite(atlas.findRegion("grass"));
	public static final Sprite wallTile = new Sprite(atlas.findRegion("wall"));
	public static final Sprite boulderTile = new Sprite(atlas.findRegion("boulder"));
	public static final Sprite chestTile = new Sprite(atlas.findRegion("chest"));
	public static final Sprite goldenDragonTile = new Sprite(atlas.findRegion("golden_dragon"));
	public static final Sprite stoneTile = new Sprite(atlas.findRegion("stone"));
	public static final Sprite treeTile = new Sprite(atlas.findRegion("tree"));
	public static final Sprite ironTrollTile = new Sprite(atlas.findRegion("iron_troll"));
	public static final Sprite bushTile = new Sprite(atlas.findRegion("bush"));

	//when given an id returns the sprite
	public static Sprite getSprite(int[] id){
		
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
