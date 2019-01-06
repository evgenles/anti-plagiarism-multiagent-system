package com.diit.antiplagiarism.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.Event;
import io.sarl.lang.core.Space;
import java.util.UUID;

@SarlSpecification("0.8")
@SarlElementType(11)
@SuppressWarnings("all")
public interface IPhysicSpace extends Space {
  public abstract UUID getCreatorID();
  
  public abstract Boolean putOnEventBus(final Event event, final UUID scope);
  
  public abstract void putOnNetwork(final Event event, final UUID scope);
}
