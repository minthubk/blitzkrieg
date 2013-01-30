package ve.com.alericoveri.blitzkrieg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Background extends Actor 
{
	Texture mTexture;
	
	public Background()
	{
		mTexture = new Texture (Gdx.files.internal("gfx/background.bmp"));
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) 
	{	
		batch.draw(mTexture, 0, 0, Blitzkrieg.SCREEN_WIDTH, Blitzkrieg.SCREEN_HEIGHT);
	}
}
