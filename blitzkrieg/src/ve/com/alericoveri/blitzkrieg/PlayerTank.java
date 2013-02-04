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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * 
 * @author Alejandro Ricoveri
 * 
 */
public class PlayerTank extends Tank {
	public static float MOVE_EPSILON = 10.0f;

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public PlayerTank(int x, int y) {
		super(x, y, 120);
		Gdx.app.log("PlayerTank", "Initiating player tank ...");
		setVisible(true);
	}
	
	/** World collision detection */
	@Override
	public void onMove (SpriteBatch batch, float parentAlpha)
	{
		boolean collision = false;
		
		// x
		if (getX() < 0) { 
			setX(0); collision = true;
		}
		else if (getX() + getWidth() > Blitzkrieg.SCREEN_WIDTH) {
			setX(Blitzkrieg.SCREEN_WIDTH - getWidth());
			collision = true;
		}
		
		// y
		if (getY() < 0) { 
			setY(0); collision = true;
		}
		else if (getY() + getHeight() > Blitzkrieg.SCREEN_HEIGHT) {
			setY(Blitzkrieg.SCREEN_HEIGHT - getHeight());
			collision = true;
		}
		
		// do the usual drawing
		super.onMove(batch, parentAlpha);
		
		// swap to still state if any collision ocurred
		if (collision)
			swap("ST_STILL");
	}

}
