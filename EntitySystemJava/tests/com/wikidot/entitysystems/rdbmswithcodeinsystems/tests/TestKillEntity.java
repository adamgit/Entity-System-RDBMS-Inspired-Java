package com.wikidot.entitysystems.rdbmswithcodeinsystems.tests;

import org.junit.Before;
import org.junit.Test;

import com.wikidot.entitysystems.rdbmswithcodeinsystems.EntityManager;

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
}
