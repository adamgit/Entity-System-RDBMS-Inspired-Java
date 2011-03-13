package com.wikidot.entitysystems.rdbmswithcodeinsystems.tests;

import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.examplecomponents.Position;

/**
 * Very brief test to check that the ES implementation is compiling and running OK
 * 
 * Run from the command line - no arguments - and see if you get Exceptions / Errors.
 */
public class quicktest
{
	
	public static void main(String[] args)
	{
		EntityManager em = new EntityManager();
		
		int entity1 = em.createEntity();
		
		em.addComponent(entity1, new Position());
		em.getComponent(entity1, Position.class).x = 5;
		em.getComponent(entity1, Position.class).y = 10;
		
		System.out.println( "Value of Position component is: "+em.getComponent(entity1, Position.class));
	}
}
