/*
 * 
 */

package ve.com.alericoveri.blitzkrieg;

import com.badlogic.gdx.Gdx;

/**
 * 
 * @author Alejandro Ricoveri
 *
 */
public class PlayerTank extends Tank
{
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public PlayerTank(int x, int y) 
	{
		super(x, y, 20);
		Gdx.app.log("PlayerTank", "Initiating player tank ...");
		setVisible(true);
	}
	
	
}
