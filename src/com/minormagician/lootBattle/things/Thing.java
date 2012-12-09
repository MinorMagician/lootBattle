package com.minormagician.lootBattle.things;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.minormagician.lootBattle.items.Inventory;

public class Thing extends Image {

	public Inventory inventory = null;
    public int[] id = SpriteFinder.airId;
    public boolean passable = true;
	public boolean isEnemy = false;

    public Thing(int[] id) {
        super(SpriteFinder.getSprite(id));
        this.id = id;
        if(id[1] == 1){
           this.passable = false;
        }
    }

    public Thing(int[] id, float x, float y) {
        super(SpriteFinder.getSprite(id));
        this.id = id;
        this.setXY(x, y);
        if(id[1] == 1){
            this.passable = false;
        }
    }

    public void setXY(float x, float y){
        this.setX(x);
        this.setY(y);
    }

    public Rectangle getBoundingRectangle(){
        return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public Thing getMini(){
        return new Thing(id, getX(), getY());
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
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

    public void setEnemy(boolean isEnemy){
        this.isEnemy = isEnemy;
    }

	public boolean isEnemy(){
		return isEnemy;
	}
	
}
