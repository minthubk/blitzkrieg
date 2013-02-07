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
 * A simple projectile
 * 
 * @author Alejandro Ricoveri
 */
abstract public class Projectile extends Actor {
	
	/** Velocity */
	protected int mVelocity;

	/** State machine */
	private StateMachine mStateMachine;

	/** Direction */
	protected Direction mDirection;

	public enum Direction {
		LEFT, RIGHT, UP, DOWN
	}

	/**
	 * Ctor
	 * 
	 * @param x
	 *            Initial left coordinate for this object
	 * @param y
	 *            Initial top coordinate for this object
	 * @param velocity
	 *            Velocity factor for this object
	 */
	public Projectile(int x, int y, int velocity) {
		// Set initial velocity
		mVelocity = velocity;

		// Initial direction
		mDirection = Direction.RIGHT;

		// Create a new state machine implementation
		mStateMachine = new StateMachine("Projectile");

		// Initial coordinates
		setX(x);
		setY(y);

		// Let this be visible at once
		setVisible(false);

		/* states for this projectile */

		// Still state
		mStateMachine.register(new State<Projectile>("ST_STILL", mStateMachine,
				this) {

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
		});

		// On movement state
		mStateMachine.register(new State<Projectile>("ST_MOVE", mStateMachine,
				this) {

			@Override
			public void onEnter() {
				mObject.onMoveBegin();
			}

			/** Calculate position each frame */
			@Override
			public void onExec(SpriteBatch batch, float parentAlpha) {

				// Time differential
				float deltaTime = Gdx.graphics.getDeltaTime();

				// Distance differential
				int dxy = (int) (mObject.mVelocity * deltaTime);

				// Calculate movement based on direction,
				// velocity and time differentials
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

				// Call the onMove callback for custom code
				mObject.onMove(batch, parentAlpha);
			}

			/** Call onMoveEnd callback */
			@Override
			public void onExit() {
				mObject.onMoveEnd();
			}
		});

		// Set initial state to still
		mStateMachine.push("ST_STILL");
	}

	/** ST_MOVE callbacks, can be overridden */
	public void onMoveBegin() {
	}

	public void onMove(SpriteBatch batch, float parentAlpha) {
	}

	public void onMoveEnd() {
	}

	/** ST_STILL callbacks, can be overridden */
	public void onStillBegin() {
	}

	public void onStill(SpriteBatch batch, float parentAlpha) {
	}

	public void onStillEnd() {
	}

	/** Pop out an state from the state machine */
	public void pop() {
		mStateMachine.pop();
	}

	/** Push an already registered state to the state machine */
	public void push(String stateName) {
		mStateMachine.push(stateName);
	}

	/** Swap to a prevoiusly registered state to the state machine */
	public void swap(String stateName) {
		mStateMachine.swap(stateName);
	}

	/** Call current state run() callback */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		mStateMachine.run(batch, parentAlpha);
	}

	/** Set direction and change state to moving mode */
	public void move(Direction direction) {
		mDirection = direction;
		mStateMachine.swap("ST_MOVE");
	}

	/** Move right */
	public void moveRight() {
		move(Direction.RIGHT);
	}

	/** Move left */
	public void moveLeft() {
		move(Direction.LEFT);
	}

	/** Move up */
	public void moveUp() {
		move(Direction.UP);
	}

	/** Move down */
	public void moveDown() {
		move(Direction.DOWN);
	}

	/** Get direction of this projectile */
	public Direction getDirection() {
		return mDirection;
	}

	/** Stop (still) this */
	public void stop() {
		mStateMachine.swap("ST_STILL");
	}
}
