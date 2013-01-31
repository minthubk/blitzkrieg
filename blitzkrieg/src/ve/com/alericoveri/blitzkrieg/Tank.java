/*
 * 
 */
package ve.com.alericoveri.blitzkrieg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

/**
 * 
 * @author Alejandro Ricoveri
 *
 */
abstract public class Tank extends Actor 
{
	/* */
	static protected Texture mTexture;
	
	/* */
	//private TextureRegion mTexRegion;
	private Sprite mSprite;
	
	/* */
	protected int mVelocity;
	
	public enum Direction {
		MOVE_LT, MOVE_RT, MOVE_UP, MOVE_DN
	}
	
	protected Direction mDirection;

	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Tank (int x, int y, int velocity)
	{
		setX(x);
		setY(y);
		setVisible(false);
		
		mVelocity = velocity;
		
		if (mTexture == null)
			mTexture = new Texture(Gdx.files.internal("gfx/tanks.png"));
		
		mSprite = new Sprite(mTexture);
		mSprite.setRegion(0, 0, 32, 32);
		
		mDirection = Direction.MOVE_RT;
		
		updateDimensions();
	}
	
	public void updateDimensions()
	{
		int width = (int) (0.2f * Blitzkrieg.SCREEN_WIDTH);
		setWidth(width);
		setHeight(width);
		mSprite.setSize(width, width);
	}
	
	/**
	 * 
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) 
	{
		mSprite.setPosition(getX(),getY());
		mSprite.draw(batch);
	}
	
	public void move(Direction direction)
	{
		MoveByAction action = new MoveByAction();
		float distance = 0.0f;
		
		switch (direction) 
		{
		
		case MOVE_RT:
		case MOVE_LT:
			distance = direction == Direction.MOVE_RT ? 
					Blitzkrieg.SCREEN_WIDTH - getX() - getWidth()
					:  - getX();
			action.setAmountX(distance);
			break;
		
		case MOVE_UP:
		case MOVE_DN:
			distance = direction == Direction.MOVE_UP ? 
					Blitzkrieg.SCREEN_HEIGHT - getY() - getHeight()
					:  - getY();
			action.setAmountY(distance);
			break;
		}
		
		clearActions();
		action.setDuration(Math.abs(distance/mVelocity));
		action.setInterpolation(Interpolation.linear);
		addAction(action);
		
		mDirection = direction;
	}
	
	public void moveRight()
	{
		if (mDirection != Direction.MOVE_RT) 
		{
			switch (mDirection) 
			{
			case MOVE_LT:
				mSprite.flip(true, false);
				break;
			case MOVE_DN:
				mSprite.rotate90(false);
				break;
			case MOVE_UP:
				mSprite.rotate90(true);
			}
			
			move (Direction.MOVE_RT);
		}
	}
	
	public void moveLeft()
	{
		if (mDirection != Direction.MOVE_LT) 
		{
			switch (mDirection) 
			{
			case MOVE_RT:
				mSprite.flip(true, false);
				break;
			case MOVE_DN:
				mSprite.rotate90(true);
				break;
			case MOVE_UP:
				mSprite.rotate90(false);
				break;
			}
			
			move (Direction.MOVE_LT);
		}
	}
	
	public void moveUp()
	{
		if (mDirection != Direction.MOVE_UP) 
		{
			switch (mDirection) 
			{
			case MOVE_LT:
				mSprite.rotate90(true);
				break;
			case MOVE_RT:
				mSprite.rotate90(false);
				break;
			case MOVE_DN:
				mSprite.flip(true, false);
				break;
			}
			
			move (Direction.MOVE_UP);
		}
	}
	
	public void moveDown()
	{
		if (mDirection != Direction.MOVE_DN) 
		{
			switch (mDirection) 
			{
			case MOVE_LT:
				mSprite.rotate90(false);
				break;
			case MOVE_RT:
				mSprite.rotate90(true);
				break;
			case MOVE_UP:
				mSprite.flip(true, false);
				break;
			}
			
			move (Direction.MOVE_DN);
		}
	}

	public Direction getDirection ()
	{
		return mDirection;
	}
}
