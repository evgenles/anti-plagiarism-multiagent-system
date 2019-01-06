package com.diit.antiplagiarism.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import io.sarl.lang.core.EventListener;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class AsyncRunner implements Runnable {
  private EventListener agent;
  
  private Event event;
  
  public AsyncRunner(final EventListener agent, final Event event) {
    this.agent = agent;
    this.event = event;
  }
  
  @Override
  public void run() {
    this.agent.receiveEvent(this.event);
  }
  
  @Override
  @Pure
  public String toString() {
    return (((("[agent=" + this.agent) + "; event=") + this.event) + "]");
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
}
