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

/**
 * Enemy tank
 * 
 * @author Alejandro Ricoveri
 * 
 */
public class EnemyTank extends Tank {
	/**
	 * Ctor
	 * 
	 * @param x
	 *            left coordinate in pixels
	 * @param y
	 *            top coordinate in pixels
	 */
	public EnemyTank(int x, int y) {
		super(x, y, 30);
	}
}
