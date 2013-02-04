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

import java.util.Stack;
import java.util.TreeMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A finite state machine
 * 
 * @author Alejandro Ricoveri
 */
public class StateMachine {
	/**
	 * An state
	 */
	abstract public static class State<T> {
		
		/** The state machine who owns this state */
		private StateMachine mParent;

		/** A generic object who can be controlled inside this state */
		protected T mObject;

		/** Key (unique name) or this state */
		protected String mKey;

		/** 
		 * This will be called when this state goes on top of his parent's
		 * stack  
		 */
		abstract public void onEnter();

		/** */
		abstract public void onExec(SpriteBatch batch, float parentAlpha);

		/** */
		abstract public void onExit();

		/** */
		public void pop() {
			mParent.pop();
		}

		/** */
		public void push(String stateName) {
			mParent.push(stateName);
		}

		/** */
		public State(String key, StateMachine parent, T object) {
			mParent = parent;
			mObject = object;
			mKey = key;
		}

		public String getKey() {
			return mKey;
		}
	}

	/** State stack */
	private Stack<State> mStates;

	/** Registered states */
	private TreeMap<String, State> mRegStates;

	/** Implementation name */
	private String mImpName;

	/** 
	 * Ctor
	 * 
	 * @param impName Implementation Name
	 */
	public StateMachine(String impName) {
		mImpName = impName;
		mStates = new Stack<State>();
		mRegStates = new TreeMap<String, State>();
		Gdx.app.log("StateMachine", "new implementation '" + mImpName + "'");
	}

	/**
	 * Pop an state from the stack
	 */
	public void pop() {
		if (!mStates.empty()) {
			mStates.firstElement().onExit();
			mStates.pop();
			if (!mStates.empty())
				mStates.firstElement().onEnter();
		}
	}

	/**
	 * Push an state to the stack, must be prevoiusly registered
	 * 
	 * @param stateName Unique name of the state to be pushed
	 */
	public void push(String stateName) {
		if (!mRegStates.isEmpty()) {
			if (mRegStates.containsKey(stateName)) {
				if (!mStates.empty())
					mStates.firstElement().onExit();
				mStates.push(mRegStates.get(stateName));
				mStates.firstElement().onEnter();
			}
		}
	}

	/** 
	 * Pop current state from the stack and then push a new one
	 * 
	 * @param stateName Unique name of the state to be pushed
	 */
	public void swap(String stateName) {
		if (mStates.firstElement().getKey() != stateName) {
			pop();
			push(stateName);
		}
	}

	/**
	 * Get current state on top of the stack
	 * 
	 * @return
	 */
	public State<?> getCurrentState() {
		if (!mStates.empty())
			return mStates.firstElement();
		return null;
	}

	/**
	 * Register a new state
	 * 
	 * @param state the state to be registered
	 */
	public void register(State<?> state) {
		if (!mRegStates.containsKey(state.getKey()))
			mRegStates.put(state.getKey(), state);
	}

	/**
	 * This will execute the current state on top of the stack
	 * 
	 * @param batch
	 * @param parentAlpha
	 */
	public void run(SpriteBatch batch, float parentAlpha) {
		if (!mStates.empty())
			mStates.firstElement().onExec(batch, parentAlpha);
	}
}
