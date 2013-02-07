/*
 * Blizkrieg: Yet another fun game for Android
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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Player tank
 * 
 * @author Alejandro Ricoveri
 * 
 */
public class PlayerTank extends Tank {
	/**
	 * Ctor
	 * 
	 * @param x
	 *            left coordinate in pixels
	 * @param y
	 *            top coordinate in pixels
	 */
	public PlayerTank() {
		super(0, 0, 0, 0, 120);
		
		// Center tank
		setX(Blitzkrieg.SCREEN_WIDTH/2 - getWidth()/2);
		setY(200);
		
		// point up the tank
		mDirection = Direction.UP;
	}

	/**
	 * World collision detection
	 * 
	 * @param batch
	 *            shared sprite batch for rendering
	 * @param alpha
	 *            channel of this object's parent
	 */
	@Override
	public void onMove(SpriteBatch batch, float parentAlpha) {
		
		// Set to true when tank has collided with stage edges
		boolean hasCollided = false;

		// x
		if (getX() < 0) {
			setX(0);
			hasCollided = true;
		} else if (getX() + getWidth() > Blitzkrieg.SCREEN_WIDTH) {
			setX(Blitzkrieg.SCREEN_WIDTH - getWidth());
			hasCollided = true;
		}

		// y
		if (getY() < 0) {
			setY(0);
			hasCollided = true;
		} else if (getY() + getHeight() > Blitzkrieg.SCREEN_HEIGHT) {
			setY(Blitzkrieg.SCREEN_HEIGHT - getHeight());
			hasCollided = true;
		}

		// do the usual drawing
		super.onMove(batch, parentAlpha);

		// swap to still state if any collision occurred
		if (hasCollided)
			swap("ST_STILL");
	}

}
