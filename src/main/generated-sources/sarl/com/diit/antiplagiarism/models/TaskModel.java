package com.diit.antiplagiarism.models;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Objects;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class TaskModel {
  public UUID TaskId;
  
  public String Name;
  
  public int Percentage;
  
  public String Path;
  
  public String AgentIp;
  
  public String State;
  
  public String Url;
  
  public TaskModel(final String name, final String url) {
    this.Name = name;
    this.Url = url;
    this.TaskId = UUID.randomUUID();
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
    TaskModel other = (TaskModel) obj;
    if (!Objects.equals(this.TaskId, other.TaskId)) {
      return false;
    }
    if (!Objects.equals(this.Name, other.Name)) {
      return false;
    }
    if (other.Percentage != this.Percentage)
      return false;
    if (!Objects.equals(this.Path, other.Path)) {
      return false;
    }
    if (!Objects.equals(this.AgentIp, other.AgentIp)) {
      return false;
    }
    if (!Objects.equals(this.State, other.State)) {
      return false;
    }
    if (!Objects.equals(this.Url, other.Url)) {
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
    result = prime * result + Objects.hashCode(this.Name);
    result = prime * result + this.Percentage;
    result = prime * result + Objects.hashCode(this.Path);
    result = prime * result + Objects.hashCode(this.AgentIp);
    result = prime * result + Objects.hashCode(this.State);
    result = prime * result + Objects.hashCode(this.Url);
    return result;
  }
}
