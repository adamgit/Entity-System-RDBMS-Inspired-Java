package com.wikidot.entitysystems.rdbmswithcodeinsystems.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.examplecomponents.Position;

public class TestPosition {
	private EntityManager em;

	@Before
	public void setUp() throws Exception {
		em = new EntityManager();
	}

	/*
	 * This is just quicktest.
	 */
	@Test
	public void test() {
		int entity1 = em.createEntity();

		em.addComponent(entity1, new Position());
		em.getComponent(entity1, Position.class).x = 5;
		em.getComponent(entity1, Position.class).y = 10;

		assertEquals("(5.0,10.0)", ""+em.getComponent(entity1, Position.class));
	}
}
