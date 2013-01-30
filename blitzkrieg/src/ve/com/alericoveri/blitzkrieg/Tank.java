/*
 * 
 */
package ve.com.alericoveri.blitzkrieg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

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
	private TextureRegion mTexRegion;
	
	/* */
	protected int mVelocity;

	
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
		mTexRegion = new TextureRegion(mTexture, 0, 0, 32, 32);
		
		updateDimensions();
	}
	
	public void updateDimensions()
	{
		int width = (int) (0.2f * Blitzkrieg.SCREEN_WIDTH);
		setWidth(width);
		setHeight(width);
	}
	
	/**
	 * 
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) 
	{
		batch.draw(mTexRegion, getX(), getY(), getWidth(), getHeight());
	}
}
