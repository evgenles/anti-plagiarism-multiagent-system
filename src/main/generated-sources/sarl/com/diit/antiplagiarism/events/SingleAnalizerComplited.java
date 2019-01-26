package com.diit.antiplagiarism.events;

import com.diit.antiplagiarism.models.StateEnum;
import com.diit.antiplagiarism.models.TaskModel;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import java.util.Objects;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SarlSpecification("0.8")
@SarlElementType(15)
@SuppressWarnings("all")
public class SingleAnalizerComplited extends Event {
  public TaskModel Task;
  
  public StateEnum State;
  
  public String AnalizerType;
  
  public String Description;
  
  @DefaultValueSource
  public SingleAnalizerComplited(final TaskModel task, final StateEnum state, final String analizerType, @DefaultValue("com.diit.antiplagiarism.events.SingleAnalizerComplited#NEW_0") final String description) {
    this.Task = task;
    this.State = state;
    this.AnalizerType = analizerType;
    this.Description = description;
  }
  
  /**
   * Default value for the parameter description
   */
  @SyntheticMember
  @SarlSourceCode("\"\"")
  private final static String $DEFAULT_VALUE$NEW_0 = "";
  
  @DefaultValueUse("com.diit.antiplagiarism.models.TaskModel,com.diit.antiplagiarism.models.StateEnum,java.lang.String,java.lang.String")
  @SyntheticMember
  public SingleAnalizerComplited(final TaskModel task, final StateEnum state, final String analizerType) {
    this(task, state, analizerType, $DEFAULT_VALUE$NEW_0);
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
    SingleAnalizerComplited other = (SingleAnalizerComplited) obj;
    if (!Objects.equals(this.AnalizerType, other.AnalizerType)) {
      return false;
    }
    if (!Objects.equals(this.Description, other.Description)) {
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
    result = prime * result + Objects.hashCode(this.AnalizerType);
    result = prime * result + Objects.hashCode(this.Description);
    return result;
  }
  
  /**
   * Returns a String representation of the SingleAnalizerComplited event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("Task", this.Task);
    builder.add("State", this.State);
    builder.add("AnalizerType", this.AnalizerType);
    builder.add("Description", this.Description);
  }
}
