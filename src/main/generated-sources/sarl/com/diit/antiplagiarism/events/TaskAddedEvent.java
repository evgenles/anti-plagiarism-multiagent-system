package com.diit.antiplagiarism.events;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import java.util.Objects;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SarlSpecification("0.8")
@SarlElementType(15)
@SuppressWarnings("all")
public class TaskAddedEvent extends Event {
  public String url;
  
  public Byte[] fileData;
  
  public TaskAddedEvent(final String url) {
    this.url = url;
  }
  
  public TaskAddedEvent(final Byte[] file) {
    this.fileData = this.fileData;
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
    TaskAddedEvent other = (TaskAddedEvent) obj;
    if (!Objects.equals(this.url, other.url)) {
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
    result = prime * result + Objects.hashCode(this.url);
    return result;
  }
  
  /**
   * Returns a String representation of the TaskAddedEvent event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("url", this.url);
    builder.add("fileData", this.fileData);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 794806289L;
}
