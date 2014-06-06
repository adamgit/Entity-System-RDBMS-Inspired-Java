package com.wikidot.entitysystems.rdbmswithcodeinsystems.tests;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;
import com.wikidot.entitysystems.rdbmswithcodeinsystems.examplecomponents.Position;

public class TestKillEntity {

	private EntityManager em;

	@Before
	public void setUp() throws Exception {
		em = new EntityManager();
	}

	@Test
	public void testKillingTwoForwards() {
		int first = em.createEntity();
		int second = em.createEntity();

		em.killEntity(first);
		em.killEntity(second);
	}

	@Test
	public void testKillingTwoBackwards() {
		int first = em.createEntity();
		int second = em.createEntity();

		em.killEntity(second);
		em.killEntity(first);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAccessingRemovedComponentIndividually() {
		int entity = em.createEntity();
		Position pos = new Position();
		pos.x = 5;
		pos.y = 10;
		em.addComponent(entity, pos);

		em.killEntity(entity);
		Position result = em.getComponent(entity, Position.class);
	}
	
	@Test
	public void testAccessingRemovedComponentList() {
		int entity = em.createEntity();
		Position pos = new Position();
		pos.x = 5;
		pos.y = 10;
		em.addComponent(entity, pos);

		em.killEntity(entity);
		List<Position> result = em.getAllComponentsOfType(Position.class);
		assertTrue(result.isEmpty());
	}
}
