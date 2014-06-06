package com.wikidot.entitysystems.rdbmswithcodeinsystems.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.examplecomponents.Position;

public class TestComponentAccess {

	private EntityManager em;

	@Before
	public void setUp() throws Exception {
		em = new EntityManager();
	}

	@Test
	public void testAccessingComponent() {
		int entity = em.createEntity();
		Position pos = new Position();
		pos.x = 5;
		pos.y = 10;
		em.addComponent(entity, pos);

		assertEquals(pos, em.getAllComponentsOfType(Position.class).get(0));
	}
}
