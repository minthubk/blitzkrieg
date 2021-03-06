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

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

/** 
 *
 */
public class TankGestureListener extends ActorGestureListener 
{
	private PlayerTank mPlayerTank;
	
	/**
	 * Ctor
	 */
	public TankGestureListener(PlayerTank tank) {
		mPlayerTank = tank;
	}
	
	@Override
	/** */
	public void fling 
	(InputEvent event, float velocityX, float velocityY, int button) 
	{
		if (Math.abs(velocityX) > Math.abs(velocityY))
		{
			// Horizontal
			if (velocityX > 0.0f)
				mPlayerTank.moveRight();
			else mPlayerTank.moveLeft();
		}
		else 
		{
			// Vertical
			if (velocityY > 0.0f)
				mPlayerTank.moveUp();
			else mPlayerTank.moveDown();
		}
	}
	
	@Override
	/** */
	public void tap (InputEvent event, float x, float y, int count, int button) {
		mPlayerTank.shoot();
	}
}
