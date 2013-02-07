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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameStage extends Stage {
	
	/** Unique instance for the player tank */
	private PlayerTank mPlayerTank;

	/** Background image */
	private Background mBackground;
	
	/**
	 * Ctor
	 */
	public GameStage ()
	{
		super (Blitzkrieg.SCREEN_WIDTH, Blitzkrieg.SCREEN_HEIGHT, true);
		
		// Create the player tank
		mPlayerTank = new PlayerTank();

		// Create the background
		mBackground = new Background();

		// Assign input listener for the whole stage
		addListener(new TankGestureListener(mPlayerTank));

		// Add the actors
		addActor(mBackground);
		addActor(mPlayerTank);

		// Setting this stage as the input processor
		Gdx.input.setInputProcessor(this);
	}
	
	public void resize()
	{
		// Set the stage viewport
		setViewport(Blitzkrieg.SCREEN_WIDTH, Blitzkrieg.SCREEN_HEIGHT, true);

		// Update dimensions for this entities as well
		mPlayerTank.updateDimensions();
		mBackground.updateDimensions();
	}
	
	public PlayerTank getPlayerTank() {
		return mPlayerTank;
	}
	
	public Background getBackground() {
		return mBackground;
	}
	
	public void spawnEnemyTank() {
		EnemyTank tank = new EnemyTank(0, 0, mPlayerTank);
		addActor(tank);
	}
}
