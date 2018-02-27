package by.bsac.lab2.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Entity implements Cloneable {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String CLONE_ERROR_MESSAGE = "Can't clone an entity.";

    private long id;

    public Entity() {
    }

    public Entity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof Entity) ) return false;

        Entity entity = (Entity) o;

        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public Entity clone() {
        try {
            return (Entity) super.clone();
        } catch (CloneNotSupportedException e) {
            LOGGER.log(Level.FATAL, CLONE_ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
}
