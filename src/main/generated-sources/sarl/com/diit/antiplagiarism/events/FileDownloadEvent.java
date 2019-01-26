package com.diit.antiplagiarism.events;

import com.diit.antiplagiarism.models.StateEnum;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
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
public class FileDownloadEvent extends Event {
  public String Url;
  
  public StateEnum State;
  
  public String Description;
  
  public UUID TaskId;
  
  @DefaultValueSource
  public FileDownloadEvent(final String url, final StateEnum state, final UUID taskId, @DefaultValue("com.diit.antiplagiarism.events.FileDownloadEvent#NEW_0") final String description) {
    this.Url = url;
    this.State = state;
    this.TaskId = taskId;
    this.Description = description;
  }
  
  /**
   * Default value for the parameter description
   */
  @SyntheticMember
  @SarlSourceCode("\"\"")
  private final static String $DEFAULT_VALUE$NEW_0 = "";
  
  @DefaultValueUse("java.lang.String,com.diit.antiplagiarism.models.StateEnum,java.util.UUID,java.lang.String")
  @SyntheticMember
  public FileDownloadEvent(final String url, final StateEnum state, final UUID taskId) {
    this(url, state, taskId, $DEFAULT_VALUE$NEW_0);
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
    FileDownloadEvent other = (FileDownloadEvent) obj;
    if (!Objects.equals(this.Url, other.Url)) {
      return false;
    }
    if (!Objects.equals(this.Description, other.Description)) {
      return false;
    }
    if (!Objects.equals(this.TaskId, other.TaskId)) {
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
    result = prime * result + Objects.hashCode(this.Url);
    result = prime * result + Objects.hashCode(this.Description);
    result = prime * result + Objects.hashCode(this.TaskId);
    return result;
  }
  
  /**
   * Returns a String representation of the FileDownloadEvent event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("Url", this.Url);
    builder.add("State", this.State);
    builder.add("Description", this.Description);
    builder.add("TaskId", this.TaskId);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -531477281L;
}
