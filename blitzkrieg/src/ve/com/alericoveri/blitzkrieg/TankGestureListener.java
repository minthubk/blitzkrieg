package ve.com.alericoveri.blitzkrieg;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class TankGestureListener extends ActorGestureListener 
{
	@Override
	public void fling 
	(InputEvent event, float velocityX, float velocityY, int button) 
	{
		if (Math.abs(velocityX) > Math.abs(velocityY))
		{
			// Horizontal
			if (velocityX > 0.0f)
				Blitzkrieg.PlayerTank.moveRight();
			else Blitzkrieg.PlayerTank.moveLeft();
		}
		else 
		{
			// Vertical
			if (velocityY > 0.0f)
				Blitzkrieg.PlayerTank.moveUp();
			else Blitzkrieg.PlayerTank.moveDown();
		}
	}
}
