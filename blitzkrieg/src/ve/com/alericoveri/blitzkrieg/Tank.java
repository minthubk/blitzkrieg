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
 *2. Altered source versions must be plainly marked as such, and must not be
 *misrepresented as being the original software.
 *
 *3. This notice may not be removed or altered from any source
 *distribution.
 */

package ve.com.alericoveri.blitzkrieg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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
abstract public class Tank extends Actor {
	/* */
	static protected Texture mTexture;

	/* */
	private Sprite mCurrentFrame;
	private Sprite[] mFrames;
	private float mStateTime = 0.0f;

	/* */
	protected int mVelocity;

	public enum Direction {
		MOVE_LT, MOVE_RT, MOVE_UP, MOVE_DN
	}

	protected Direction mDirection;

	private Animation mAnim;

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Tank(int x, int y, int velocity) {
		setX(x);
		setY(y);
		setVisible(false);

		mVelocity = velocity;
		mDirection = Direction.MOVE_RT;

		if (mTexture == null)
			mTexture = new Texture(Gdx.files.internal("gfx/tanks.png"));

		mCurrentFrame = new Sprite();
		mFrames = new Sprite[8];

		for (int i = 0; i < 8; i++) {
			mFrames[i] = new Sprite(mTexture);
			mFrames[i].setRegion(i * 32, 0, 32, 32);
		}

		mAnim = new Animation(0.05f, mFrames);

		updateDimensions();
	}

	public void updateDimensions() {
		int width = (int) (0.2f * Blitzkrieg.SCREEN_WIDTH);

		setWidth(width);
		setHeight(width);

		mCurrentFrame.setSize(width, width);
	}

	/**
	 * 
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		mStateTime += Gdx.graphics.getDeltaTime();
		mCurrentFrame.setRegion(mAnim.getKeyFrame(mStateTime, true));
		mCurrentFrame.setPosition(getX(), getY());

		switch (mDirection) {
		case MOVE_UP:
			mCurrentFrame.rotate90(false);
			break;
		case MOVE_DN:
			mCurrentFrame.rotate90(true);
			break;
		case MOVE_LT:
			mCurrentFrame.flip(true, false);
			break;
		case MOVE_RT:
			/* this is the default position */
			break;
		}

		mCurrentFrame.draw(batch);
	}

	public void move(Direction direction) {
		MoveByAction action = new MoveByAction();
		float distance = 0.0f;

		switch (direction) {

		case MOVE_RT:
		case MOVE_LT:
			distance = direction == Direction.MOVE_RT ? Blitzkrieg.SCREEN_WIDTH
					- getX() - getWidth() : -getX();
			action.setAmountX(distance);
			break;

		case MOVE_UP:
		case MOVE_DN:
			distance = direction == Direction.MOVE_UP ? Blitzkrieg.SCREEN_HEIGHT
					- getY() - getHeight()
					: -getY();
			action.setAmountY(distance);
			break;
		}

		clearActions();
		action.setDuration(Math.abs(distance / mVelocity));
		action.setInterpolation(Interpolation.linear);
		addAction(action);

		mDirection = direction;
	}

	public void moveRight() {
		move(Direction.MOVE_RT);
	}

	public void moveLeft() {
		move(Direction.MOVE_LT);
	}

	public void moveUp() {
		move(Direction.MOVE_UP);
	}

	public void moveDown() {
		move(Direction.MOVE_DN);
	}

	public Direction getDirection() {
		return mDirection;
	}
}
