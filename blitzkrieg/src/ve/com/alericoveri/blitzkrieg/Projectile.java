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

import ve.com.alericoveri.blitzkrieg.StateMachine.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * 
 * @author Alejandro Ricoveri
 *
 */
abstract public class Projectile extends Actor
{
	protected int mVelocity;
	private StateMachine mStateMachine;
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
		mStateMachine = new StateMachine("Projectile");
		
		setX(x);
		setY(y);
		setVisible(false);
		
		// states
		mStateMachine.register(
		new State<Projectile>("ST_STILL", mStateMachine, this) {
			
			@Override
			public void onEnter() {
				mObject.onStillBegin();
			}

			@Override
			public void onExec(SpriteBatch batch, float parentAlpha) {
				mObject.onStill(batch, parentAlpha);
			}

			@Override
			public void onExit() {
				mObject.onStillEnd();
			}
		}
		);
		
		mStateMachine.register( 
		new State<Projectile>("ST_MOVE", mStateMachine, this) {
			
			@Override
			public void onEnter() {
				mObject.onMoveBegin();
			}

			@Override
			public void onExec(SpriteBatch batch, float parentAlpha) {
				
				float deltaTime = Gdx.graphics.getDeltaTime();
				int dxy =  (int) (mObject.mVelocity * deltaTime);
				
				switch (mDirection) {
				case UP:
					mObject.setPosition(getX(), getY() + dxy);
					break;
				case DOWN:
					mObject.setPosition(getX(), getY() - dxy);
					break;
				case LEFT:
					mObject.setPosition(getX() - dxy, getY());
					break;
				case RIGHT:
					/* this is the default position */
					mObject.setPosition(getX() + dxy, getY());
					break;
				}
				mObject.onMove(batch, parentAlpha);
			}

			@Override
			public void onExit() {
				mObject.onMoveEnd();
			}
		}
		);
		
		mStateMachine.push("ST_STILL");
	}
	
	/** */
	public void onMoveBegin () {}
	public void onMove (SpriteBatch batch, float parentAlpha) {}
	public void onMoveEnd () {}
	
	/** */
	public void onStillBegin () {}
	public void onStill (SpriteBatch batch, float parentAlpha) {}
	public void onStillEnd () {}
	
	/** */
	public void pop () {
		mStateMachine.pop();
	}
	
	/** */
	public void push (String stateName) {
		mStateMachine.push(stateName);
	}
	
	/** */
	public void swap (String stateName) {
		mStateMachine.swap(stateName);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) 
	{
		mStateMachine.run(batch, parentAlpha);
	}
	
	public void move(Direction direction) 
	{
		mDirection = direction;
		mStateMachine.swap("ST_MOVE");
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
	
	public void stop()
	{
		mStateMachine.swap("ST_STOP");
	}
}
