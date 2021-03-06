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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * A background image
 * 
 * @author Alejandro Ricoveri
 */
public class Background extends Actor {
	
	/** The image texture */
	Texture mTexture;

	/** Ctor */
	public Background() {
		mTexture = new Texture(Gdx.files.internal("gfx/background.bmp"));
		updateDimensions();
	}

	/** Update width and height (in pixels) covering the entire screen */
	public void updateDimensions() {
		setWidth(Blitzkrieg.SCREEN_WIDTH);
		setHeight(Blitzkrieg.SCREEN_HEIGHT);
	}

	/** Render the image */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(mTexture, 0, 0, getWidth(), getHeight());
	}
}
