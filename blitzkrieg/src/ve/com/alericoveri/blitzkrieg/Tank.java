/*
 * Blizkrieg: Yet another fun and simple game
 * Copyright (c) 2013 Alejandro Ricoveri <alejandroricoveri@gmail.com>
 * 
 * This software is provided 'as-is', without any express or implied
 * warranty. In no event will the authors be held liable for any damages
 * arising from the use of this software.
 *
 * Permission is granted to anyone to use this software for any purpose,
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:
 *
 * 1. The origin of this software must not be misrepresented; you must not
 * claim that you wrote the original software. If you use this software
 * in a product, an acknowledgment in the product documentation would be
 * appreciated but is not required.
 *
 * 2. Altered source versions must be plainly marked as such, and must not be
 * misrepresented as being the original software.
 *
 * 3. This notice may not be removed or altered from any source
 * distribution.
 */

package ve.com.alericoveri.blitzkrieg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A Tank
 * 
 * @author Alejandro Ricoveri
 * 
 */
abstract public class Tank extends Projectile {
	
	/** Image asset used for the tanks */
	static protected Texture mTexture;

	/** Current frame to be rendered */
	private Sprite mCurrentFrame;
	
	/** All frames used for the tank's animation */
	private Sprite[] mFrames;
	
	/** Reference time variable to be used on animation */
	private float mStateTime = 0.0f;

	/** The animation instance */
	private Animation mAnim;

	/**
	 * Ctor
	 * 
	 * @param x Initial top coordinate for this object
	 * @param y Initial left coordinate for this object
	 * @param velocity Initial velocity for this object
	 */
	public Tank(int x, int y, int spriteOffsetX, int spriteOffsetY, int velocity) 
	{
		super (x, y, velocity);

		if (mTexture == null)
			mTexture = new Texture(Gdx.files.internal("gfx/tanks.png"));

		mCurrentFrame = new Sprite();
		mFrames = new Sprite[8];

		// Create frames based on tecture
		for (int i = 0; i < 8; i++) {
			mFrames[i] = new Sprite(mTexture);
			mFrames[i].setRegion(spriteOffsetX + i * 32, spriteOffsetY, 32, 32);
		}

		// Create animation
		mAnim = new Animation(0.05f, mFrames);
		
		// Update dimensions for this tank
		updateDimensions();
		
		// Show up
		setVisible(true);
	}
	
	/** 
	 * Update dimensions for this object
	 */
	public void updateDimensions() {
		
		// calculate width relative to the screen width
		int width = (int) (0.2f * Blitzkrieg.SCREEN_WIDTH);

		// set dimensions
		setWidth(width);
		setHeight(width);

		// set current frame size
		mCurrentFrame.setSize(width, width);
	}
	
	/** Move animation */
	@Override
	public void onMove (SpriteBatch batch, float parentAlpha)
	{
		mStateTime += Gdx.graphics.getDeltaTime();
		mCurrentFrame.setRegion(mAnim.getKeyFrame(mStateTime, true));
		mCurrentFrame.setPosition(getX(), getY());
		flip();
	}
	
	/** Shoot a bullet */
	public void shoot()
	{
		getStage().addActor(new Bullet(this));
	}
	
	/** Flip sprite depending on current direction */
	protected void flip()
	{
		switch (mDirection) {
		case UP:
			mCurrentFrame.rotate90(false);
			break;
		case DOWN:
			mCurrentFrame.rotate90(true);
			break;
		case LEFT:
			mCurrentFrame.flip(true, false);
			break;
		case RIGHT:
			/* this is the default position */
			break;
		}
	}
	
	/** */
	@Override
	public void onStill (SpriteBatch batch, float parentAlpha)
	{
		mCurrentFrame.setRegion(mFrames[0]);
		mCurrentFrame.setPosition(getX(), getY());
		flip();
	}
	
	/** */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) 
	{
		super.draw(batch, parentAlpha);
		mCurrentFrame.draw(batch);
	}
}
