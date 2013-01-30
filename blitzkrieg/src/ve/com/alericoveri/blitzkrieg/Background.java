package ve.com.alericoveri.blitzkrieg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;


public class Background extends Actor 
{
	Texture mTexture;
	int mLastX = 0;
	float delta;
	
	public Background()
	{
		mTexture = new Texture (Gdx.files.internal("gfx/background.bmp"));
		
		addListener(new InputListener() {
			
			public boolean touchDown
			(InputEvent event, float x, float y, int pointer, int button)
			{
				delta = x - mLastX;
				if (delta > PlayerTank.MOVE_EPSILON 
	        			|| delta < -PlayerTank.MOVE_EPSILON) {
					mLastX = (int) x;
					return true;
				}
				mLastX = (int) x;
				return false;
				
			}
			
	        public void touchDragged
	        (InputEvent event, float x, float y, int pointer) 
	        {
	        	
	        	if (delta > 0)
	        		Blitzkrieg.mPlayTank.moveRight();
	        	else Blitzkrieg.mPlayTank.moveLeft();
	
	        }
	        
	        public void touchUp 
	        (InputEvent event, float x, float y, int pointer, int button) 
	        {
	        	Gdx.app.log("Background", "up");
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
