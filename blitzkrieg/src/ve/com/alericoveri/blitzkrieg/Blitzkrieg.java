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

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * Game entry point
 * 
 * @author Alejandro Ricoveri
 */
public class Blitzkrieg implements ApplicationListener {
	// whatever screen width and height we want to have in the desktop version
	/** Screen width */
	public static int SCREEN_WIDTH;

	/** Screen height in pixels */
	public static int SCREEN_HEIGHT;

	/** Game stage */
	private GameStage mStage;

	/** Initialization method */
	@Override
	public void create() {
		// we'll get whatever the set width is- 800x480 above,
		// but will be the device resolution when running the android version
		SCREEN_WIDTH 	= Gdx.graphics.getWidth();
		SCREEN_HEIGHT 	= Gdx.graphics.getHeight();

		/* set up the stage */
		mStage = new GameStage();
	}

	/** Get rid of this object */
	@Override
	public void dispose() {
		mStage.dispose();
	}

	/** Render the screen */
	@Override
	public void render() {
		/* Clear color */
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/* Draw the scene! */
		mStage.act(Gdx.graphics.getDeltaTime());
		mStage.draw();
	}

	/** This is called everything gfx context is resized */
	@Override
	public void resize(int width, int height) {

		// Set width and height
		SCREEN_WIDTH 	= width;
		SCREEN_HEIGHT 	= height;
		
		mStage.resize();
	}

	/** Game has been paused */
	@Override
	public void pause() {
	}

	/** Game has resumed */
	@Override
	public void resume() {
	}
}
