package com.diit.antiplagiarism.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;
import io.sarl.lang.core.EventListener;
import io.sarl.lang.core.Scope;
import io.sarl.lang.core.Space;
import io.sarl.lang.util.SynchronizedSet;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.8")
@SarlElementType(11)
@SuppressWarnings("all")
public interface IPhysicSpace extends Space {
  @Pure
  public abstract SynchronizedSet<UUID> getParticipants();
  
  public abstract UUID getCreatorID();
  
  public abstract Boolean putOnEventBus(final Event event, final UUID scope);
  
  public abstract void putOnNetwork(final Event event, final UUID scope);
  
  public abstract void bindBody(final EventListener agent);
  
  public abstract void unbindBody(final EventListener agent);
  
  public abstract void emit(final UUID eventSource, final Event event, final Scope<Address> scope);
}
