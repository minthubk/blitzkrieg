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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 * @author Alejandro Ricoveri
 *
 */
public class Bullet extends Projectile 
{
	/** Time to live (in seconds) */
	public static float TTL = 1f;
	
	private Tank mTank;
	float time = 0f;
	ShapeRenderer shapeRenderer;
	
	/**
	 * Ctor
	 * 
	 * @param tank Owner of this bullet
	 */
	public Bullet(Tank tank) {
		super(0, 0, 350);
		mTank = tank;
		setX(tank.getX() + tank.getWidth()/2  + 8); 
		setY(tank.getY() + tank.getHeight()/2 + 8);
		setColor(Color.WHITE);
		setWidth(8); setHeight(8);
		setVisible(true);
		move (mTank.getDirection());
		shapeRenderer = new ShapeRenderer();
		
	}
	
	public void onMove (SpriteBatch batch, float parentAlpha)
	{
		Color color = getColor();
        //batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        //batch.draw ();
		
		shapeRenderer.begin(ShapeType.FilledCircle);
		shapeRenderer.setColor(Color.PINK);
		shapeRenderer.filledCircle(getX(), getY(), 8);
		shapeRenderer.end();
		
		time += Gdx.graphics.getDeltaTime();
		if (time > TTL)
			getStage().getRoot().removeActor(this);
	}

}
