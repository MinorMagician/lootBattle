package com.minormagician.lootBattle.things;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.minormagician.lootBattle.items.Inventory;

public class Thing {
	
	public float x, y, width, height;
	public float rotation;
	public Sprite sprite;
	public Inventory inventory = null;
	
	public int[] id = SpriteFinder.airId;
	
	public boolean passable = true;
	public boolean isEnemy = false;
	
	public Thing(float x, float y, float width, float height, int[] id, boolean isEnemy, Inventory inventory) {
		this.sprite = SpriteFinder.getSprite(id);
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		if(id[1] == 1){
			this.passable = false;
		}
		this.isEnemy = isEnemy;
		this.inventory = inventory;
	}
	
	//Set everything position dimension and Id, Texture, passable, isEnemy
	public Thing(float x, float y, float width, float height, int[] id, boolean isEnemy) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		this.sprite = SpriteFinder.getSprite(id);
		if(id[1] == 1){
			this.passable = false;
		}
		this.isEnemy = isEnemy;
	}
	
	//Set everything except isEnemy, which is false by default
	public Thing(float x, float y, float width, float height, int[] id) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		this.sprite = SpriteFinder.getSprite(id);
		if(id[1] == 1){
			this.passable = false;
		}
		}
	
	
	public Thing(int id[]){
		this.sprite = SpriteFinder.getSprite(id);
		if(id[1] == 1){
			this.passable = false;
		}
		this.x = sprite.getX();
		this.y = sprite.getY();
		width = sprite.getWidth();
		height = sprite.getHeight();
	}
	
	public Thing(int x, int y, int[] id){
		this.id = id;
		this.sprite = SpriteFinder.getSprite(id);
		if(id[1] == 1){
			passable = false;
		}
		this.x = x;
		this.y = y;
		width = sprite.getWidth();
		height = sprite.getHeight();
	}
	
	//sets thing as thing
	public Thing (Thing thing) {
		x = thing.x;
		y = thing.y;
		width = thing.width;
		height = thing.height;
		id = thing.id;
//		sprite = thing.sprite;
		if(id[1] == 1){
			this.passable = false;
		}
		isEnemy = thing.isEnemy;
		inventory = thing.inventory;
	}
	
	//returns the texture of the thing
	public Sprite getSprite(Thing thing){
	return sprite;
	}
		
	//find out if it overlaps
	public boolean overlaps (Thing thing) {
		return !(x > thing.x + thing.width || x + width < thing.x || y > thing.y + thing.height || y + height < thing.y);
	}
	
	//sets a thing as a thing without is enemy
	public void set(float x, float y, float width, float height, int[] id, Sprite sprite, boolean passable) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		this.sprite = sprite;
		if(id[1] == 1){
			this.passable = false;
		}
	}
	
	//make a thing this other thing
	public void set(Thing thing) {
		x = thing.x;
		y = thing.y;
		width = thing.width;
		height = thing.height;
		id = thing.id;
		sprite = thing.sprite;
		if(id[1] == 1){
			this.passable = false;
		}
		isEnemy = thing.isEnemy;
		inventory = thing.inventory;
	}
	
	public void setRotation (float degrees) {
		this.rotation = degrees;
	}
	
	public boolean hasInventory(){
		if(inventory != null){
			return true;
		}
		return false;
	}
	
	public Inventory getInventory(){
		return inventory;
	}
	
	public boolean isEnemy(){
		return isEnemy;
	}
	
}
