package com.diit.antiplagiarism.events;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import java.util.Objects;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SarlSpecification("0.8")
@SarlElementType(15)
@SuppressWarnings("all")
public class TaskComplited extends Event {
  public UUID TaskId;
  
  public String State;
  
  public TaskComplited(final UUID taskId, final String state) {
    this.TaskId = taskId;
    this.State = state;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    TaskComplited other = (TaskComplited) obj;
    if (!Objects.equals(this.TaskId, other.TaskId)) {
      return false;
    }
    if (!Objects.equals(this.State, other.State)) {
      return false;
    }
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Objects.hashCode(this.TaskId);
    result = prime * result + Objects.hashCode(this.State);
    return result;
  }
  
  /**
   * Returns a String representation of the TaskComplited event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("TaskId", this.TaskId);
    builder.add("State", this.State);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 1893866986L;
}
