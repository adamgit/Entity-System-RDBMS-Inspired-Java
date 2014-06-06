package com.wikidot.entitysystems.rdbmswithcodeinsystems;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Standard design: c.f. http://entity-systems.wikidot.com/rdbms-with-code-in-systems
 * 
 * Modified in java to use Generics: instead of having a "ComponentType" enum, we use the class type
 * of each subclass instead. This is safer.
 */

public class EntityManager
{
	int lowestUnassignedEntityID=1;
	List<Integer> allEntities;
	HashMap<Class, HashMap<Integer, ? extends Component>> componentStores;
	
	public EntityManager()
	{
		allEntities = new LinkedList<Integer>();
		componentStores = new HashMap<Class, HashMap<Integer,? extends Component>>();
	}
	
	public <T extends Component> T getComponent( int entity, Class<T> componentType)
	{
	   HashMap<Integer, ? extends Component> store = componentStores.get( componentType );
	   
	   if( store == null)
	   	throw new IllegalArgumentException( "GET FAIL: there are no entities with a Component of class: "+componentType );
	   
	   T result = (T) store.get( entity );
	   if( result == null )
	      throw new IllegalArgumentException( "GET FAIL: "+entity+" does not possess Component of class\n   missing: "+componentType );

	   return result;
	}

	public <T extends Component> List<T> getAllComponentsOfType( Class<T> componentType )
	{
		HashMap<Integer, ? extends Component> store = componentStores.get( componentType );
		
		if( store == null )
		{
			return new LinkedList<T>();
		}
		else
		{
			List<T> result = new ArrayList<T>((java.util.Collection<T>)store.values());
			return result;
		}
	}
	
	public <T extends Component> Set<Integer> getAllEntitiesPossessingComponent( Class<T> componentType )
	{
		HashMap<Integer, ? extends Component> store = componentStores.get( componentType );
		
		if( store == null)
	   	return new HashSet<Integer>();
		
		return store.keySet();
	}
	
	public <T extends Component> void addComponent( int entity, T component )
	{
		HashMap<Integer, ? extends Component> store = componentStores.get( component.getClass() );
		
		if( store == null )
		{
			store = new HashMap<Integer, T>();
			componentStores.put(component.getClass(), store );
		}
		
		((HashMap<Integer, T>)store).put(entity, component);
	}
	
	public int createEntity()
	{

		int newID = generateNewEntityID();
		
		if( newID < 1 )
		{
			/**
			 Fatal error...
			 */
			return 0;
		}
		else
		{
			allEntities.add(newID);
		
			return newID;
		}
	}
	
	public void killEntity( Integer entity ) // Pass as an object to remove key instead of index
	{
		synchronized( this ) // prevent it generating two entities with same ID at once
		{
			allEntities.remove(entity);
			for( HashMap<Integer, ? extends Component> store : componentStores.values() )
			{
				store.remove(entity);
			}
		}
	}
	
	public int generateNewEntityID()
	{
		synchronized( this ) // prevent it generating two entities with same ID at once
		{
		if( lowestUnassignedEntityID < Integer.MAX_VALUE )
		{
			return lowestUnassignedEntityID++;
		}
		else
		{
			for( int i=1; i<Integer.MAX_VALUE; i++ )
			{
				if( ! allEntities.contains(i) )
					return i;
			}
			
			throw new Error("ERROR: no available Entity IDs; too many entities!" );
		}
		}
	}
}