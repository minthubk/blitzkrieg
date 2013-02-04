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

import ve.com.alericoveri.blitzkrieg.Tank.State;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class Projectile extends Actor
{
	private int mVelocity;
	protected Direction mDirection;
	
	public enum Direction {
		LEFT, 
		RIGHT, 
		UP, 
		DOWN
	}
	
	public Projectile(int x, int y, int velocity)
	{
		mVelocity = velocity;
		mDirection = Direction.RIGHT;
		
		setX(x);
		setY(y);
		setVisible(false);
	}
	
	public void move(Direction direction) 
	{
		MoveByAction action = new MoveByAction();
		float distance = 0.0f;

		switch (direction) 
		{
		case RIGHT:
		case LEFT:
			distance = direction == Direction.RIGHT ? Blitzkrieg.SCREEN_WIDTH
					- getX() - getWidth() : -getX();
			action.setAmountX(distance);
			break;

		case UP:
		case DOWN:
			distance = direction == Direction.UP ? Blitzkrieg.SCREEN_HEIGHT
					- getY() - getHeight()
					: -getY();
			action.setAmountY(distance);
			break;
		}

		clearActions();
		action.setDuration(Math.abs(distance / mVelocity));
		action.setInterpolation(Interpolation.linear);
		//action.
		addAction(action);
		mDirection = direction;
		
		mState = State.MOVING;
	}

	public void moveRight() {
		move(Direction.RIGHT);
	}

	public void moveLeft() {
		move(Direction.LEFT);
	}

	public void moveUp() {
		move(Direction.UP);
	}

	public void moveDown() {
		move(Direction.DOWN);
	}

	public Direction getDirection() {
		return mDirection;
	}
}
