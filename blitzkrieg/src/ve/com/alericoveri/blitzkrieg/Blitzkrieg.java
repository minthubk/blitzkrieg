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
		
	private Stage mStage;
	public static PlayerTank mPlayTank;
	public static Background mBackground;
	
	
	@Override
	public void create() 
	{
		//we'll get whatever the set width is- 800x480 above, but will be the device resolution when running the android version
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		
		/* set up the stage */
		setupStage();
	}
	
	public void setupStage()
	{
		//mCamera = new OrthographicCamera(mScreenWidth, mScreenHeight);
		
		mStage = new Stage(SCREEN_WIDTH, SCREEN_HEIGHT, true);
		mPlayTank = new PlayerTank(0,0);
		mBackground = new Background();
		
		mStage.addActor(mBackground);
		mStage.addActor(mPlayTank);
		Gdx.input.setInputProcessor(mStage);
	}
	
	public PlayerTank getPlayerTank()
	{
		return mPlayTank;
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
		mPlayTank.updateDimensions();
		mBackground.updateDimensions();
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
