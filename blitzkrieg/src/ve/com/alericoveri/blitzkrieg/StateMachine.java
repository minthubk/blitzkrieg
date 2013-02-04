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
 * 2. Altered source versions must be plainly marked as such, and must not be
 * misrepresented as being the original software.
 *
 * 3. This notice may not be removed or altered from any source
 * distribution.
 */

package ve.com.alericoveri.blitzkrieg;

import java.util.Map;
import java.util.Stack;

/**
 * 
 * @author alejandro
 *
 */
public class StateMachine 
{
	/**
	 * 
	 * @author alejandro
	 *
	 */
	abstract public class State 
	{
		/** */
		private StateMachine mParent;
		
		/** */
		abstract public void onEnter();
		
		/** */
		abstract public void onExec();
		
		/** */
		abstract public void onExit();
		
		/** */
		public void pop () {
			mParent.pop();
		}
		
		/** */
		public void push (String stateName) {
			mParent.push(stateName);
		}
		
		/** */
		public State (StateMachine parent) {
			mParent = parent;
		}
	}
	
	/** */ 
	private Stack<State> mStates;
	
	/** */
	private Map<String, State> mRegStates;
	
	/** */
	private String mImpName;
	
	/** */
	public StateMachine (String impName) {
		mImpName 	= impName;
		mStates 	= new Stack<State>();
		//mRegStates 	= new Map<String,State>();
	}
	
	/** */
	public void pop () {
		if (!mStates.empty()) {
			mStates.firstElement().onExit();
			mStates.pop();
			if (!mStates.empty())
				mStates.firstElement().onEnter();
		}
	}
	
	/** */
	public void push (String stateName) {
		if (!mRegStates.isEmpty()) {
			if (mRegStates.containsValue(stateName)) {
				if (!mStates.empty())
					mStates.firstElement().onExit();
				mStates.push(mRegStates.get(stateName));
				mStates.firstElement().onEnter();
			}
		}
	}
	
	/** */
	public void swap (String stateName) {
		pop();
		push(stateName);
	}
	
	/** */
	public State getCurrentState () {
		if (!mStates.empty())
			return mStates.firstElement();
		return null;
	}
	
	/** */
	public void register (String stateName, State state) {
		if (!mRegStates.containsKey(stateName))
			mRegStates.put(stateName, state);
	}
	
	/** */
	public void run () {
		if (!mStates.empty())
			mStates.firstElement().onExec();
	}
}
