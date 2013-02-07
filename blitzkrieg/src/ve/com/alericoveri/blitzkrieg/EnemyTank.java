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
 * 2. Altered source versions must be plainly marked as such, and must not be
 * misrepresented as being the original software.
 *
 * 3. This notice may not be removed or altered from any source
 * distribution.
 */

package ve.com.alericoveri.blitzkrieg;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Enemy tank
 * 
 * @author Alejandro Ricoveri
 * 
 */
public class EnemyTank extends Tank {
	/**
	 * Ctor
	 * 
	 * @param x
	 *            left coordinate in pixels
	 * @param y
	 *            top coordinate in pixels
	 */
	
	public enum Mode {
		ATTACK, EVASION
	}
	
	private Mode mMode = Mode.ATTACK;
	
	private PlayerTank mPlayerTank;
	
	public EnemyTank(int x, int y, PlayerTank tank) {
		super(x, y, 0, 33, 110);
		mPlayerTank = tank;
		moveUp();
	}
	
	public void onMove(SpriteBatch batch, float parentAlpha) {
		int   x = (int) getX(), 
			  y = (int) getY(), 
			  tX = (int) mPlayerTank.getX(),
			  tY = (int) mPlayerTank.getY();
		
		int dx = Math.abs(tX - x);
		int dy = Math.abs(tY - y);
		
		
		float tan = (dy > 0) ? dx/dy : 0f;
		
		if (mMode == Mode.ATTACK)
		{
			if (dy > 0) {
				if (tan > 1.5f || tan < 0.5f) 
				{
					if (dx >= dy) {
						if (tX > x)
							mDirection = Direction.RIGHT;
						else mDirection = Direction.LEFT;
					}
					else if (dy > dx) {
						if (tY > y)
							mDirection = Direction.UP;
						else mDirection = Direction.DOWN;
					}
				}
			}
			//else mDirection = mPlayerTank.getDirection();
		}
		super.onMove(batch, parentAlpha);
	}
}
