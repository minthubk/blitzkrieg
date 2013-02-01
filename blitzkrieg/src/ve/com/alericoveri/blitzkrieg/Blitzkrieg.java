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

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Blitzkrieg implements ApplicationListener 
{
	//whatever screen width and height we want to have in the desktop version
	public static int SCREEN_WIDTH 	= 800;
	public static int SCREEN_HEIGHT = 480;
	public static PlayerTank PlayerTank;
	public static Background Background;
		
	private Stage mStage;
	
	
	@Override
	public void create() 
	{
		// we'll get whatever the set width is- 800x480 above, 
		// but will be the device resolution when running the android version
		SCREEN_WIDTH 	= Gdx.graphics.getWidth();
		SCREEN_HEIGHT 	= Gdx.graphics.getHeight();
		
		/* set up the stage */
		setupStage();
	}
	
	public void setupStage()
	{
		mStage = new Stage(SCREEN_WIDTH, SCREEN_HEIGHT, true);
		
		PlayerTank = new PlayerTank(0,0);
		Background = new Background();
		
		mStage.addListener(new TankGestureListener());
		
		
		mStage.addActor(Background);
		mStage.addActor(PlayerTank);
		
		Gdx.input.setInputProcessor(mStage);
	}
	
	@Override
	public void dispose() 
	{
		mStage.dispose();
	}

	@Override
	public void render() 
	{	
		/* Clear color */
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
        
        /* Draw the scene! */
		mStage.act(Gdx.graphics.getDeltaTime());
		mStage.draw();
	}

	@Override
	public void resize(int width, int height) 
	{
		SCREEN_WIDTH 	= Gdx.graphics.getWidth();
		SCREEN_HEIGHT 	= Gdx.graphics.getHeight();
		
		mStage.setViewport(width, height, true);
		
		PlayerTank.updateDimensions();
		Background.updateDimensions();
	}

	@Override
	public void pause() 
	{
	}

	@Override
	public void resume() 
	{
	}
}
