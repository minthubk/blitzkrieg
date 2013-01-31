package ve.com.alericoveri.blitzkrieg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;


public class Background extends Actor 
{
	Texture mTexture;
	
	public Background()
	{
		mTexture = new Texture (Gdx.files.internal("gfx/background.bmp"));
		
		addListener (new ActorGestureListener() 
		{
			public void fling 
			(InputEvent event, float velocityX, float velocityY, int button) 
			{
				if (Math.abs(velocityX) > Math.abs(velocityY))
				{
					// Horizontal
					if (velocityX > 0.0f)
						Blitzkrieg.mPlayTank.moveRight();
					else Blitzkrieg.mPlayTank.moveLeft();
				}
				else 
				{
					// Vertical
					if (velocityY > 0.0f)
						Blitzkrieg.mPlayTank.moveUp();
					else Blitzkrieg.mPlayTank.moveDown();
				}
			}
		});
		
		updateDimensions();
	}
	
	public void updateDimensions()
	{
		setWidth(Blitzkrieg.SCREEN_WIDTH);
		setHeight(Blitzkrieg.SCREEN_HEIGHT);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) 
	{	
		batch.draw(mTexture, 0, 0, getWidth(), getHeight());
	}
}
